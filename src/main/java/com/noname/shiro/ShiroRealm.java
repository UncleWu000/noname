package com.noname.shiro;

import com.noname.bo.user.CSSubject;
import com.noname.entity.*;
import com.noname.mapper.*;
import com.noname.util.JsonUtil;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.print.DocFlavor;
import java.util.*;

public class ShiroRealm extends AuthorizingRealm{

    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

    @Autowired
    PermissionMapper permissionMapper;

    @Autowired
    RolePermissionMapper rolePermissionMapper;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        System.out.println("token -> " + token.toString());
        System.out.println("身份认证方法：MyShiroRealm.doGetAuthenticationInfo()");
        String subjectStr = token.getUsername();
        CSSubject csSubject = JsonUtil.fromJson(subjectStr, CSSubject.class);
        String username = csSubject.getUsername();
        String password = String .valueOf(token.getPassword());
        System.out.println(username + "正尝试登陆...");

        User user = new User();
        user.setNickname(username);
        List<User> users = userMapper.select(user);
        System.out.println(Arrays.toString(users.toArray()));
        if(users.size()>0){
            user = users.get(0);
        }

        if(user==null){
            throw new AccountException("账号或者密码不正确!");
        }else if("0".equals(user.getStatus())){
            throw new  DisabledAccountException("该账号已被封停,请联系管理员!");
        }else{
            user.setLastLoginTime(new Date());
            userMapper.updateByPrimaryKey(user);
        }

        return new SimpleAuthenticationInfo(user, password, getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Long userId = user.getId();

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //查找用户拥有的角色及权限
        Set<String> roleSet = new HashSet<>();
        Set<String> rolePermissions = new HashSet<>();
        UserRole userRole = null;
        RolePermission rolePermission = null;
        Permission permission = null;
        userRole.setUid(userId);
        System.out.println(user.getNickname() + "正尝试登陆...");
        System.out.println("角色类型:{");
        List<UserRole> userRoles = userRoleMapper.select(userRole);
        userRoles.forEach((ur)->{
            Role role = roleMapper.selectByPrimaryKey(ur.getRid());
            roleSet.add(role.getName());
            System.out.println(role.getName() + "-权限:[");
            rolePermission.setRid(ur.getRid());
            rolePermissionMapper.select(rolePermission).forEach(rp->{
                    permission.setId(rp.getRid());
                    permissionMapper.select(permission).forEach(p->{
                        rolePermissions.add(p.getName());
                        System.out.println(p.getName()+" ");
                    });
            });
            System.out.println("]");
        });
        System.out.println("}");

        info.setRoles(roleSet);
        info.setStringPermissions(rolePermissions);
        return info;
    }
}


