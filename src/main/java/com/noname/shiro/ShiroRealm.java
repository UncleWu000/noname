package com.noname.shiro;

import com.noname.entity.*;
import com.noname.mapper.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Long userId = user.getId();

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //查找用户拥有的角色和拥有的权限
        Set<String> roleSet = new HashSet<>();
        Set<String> rolePermissions = new HashSet<>();
        UserRole userRole = null;
        RolePermission rolePermission = null;
        Permission permission = null;
        List<RolePermission> permissions = null;
        userRole.setUid(userId);
        List<UserRole> userRoles = userRoleMapper.select(userRole);
        userRoles.forEach(s->{
            Role role = roleMapper.selectByPrimaryKey(s.getRid());
            roleSet.add(role.getName());
            rolePermission.setRid(role.getId());
        });

        //



        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        String password = String .valueOf(token.getPassword());

        List<User> users = userMapper.selectAll();
        User user = null;
        if(users.size()>0){
            user = users.get(0);
        }

        if(user!=null){
            throw new AccountException("账号或者密码不正确!");
        }else if("0".equals(user.getStatus())){
            throw new  DisabledAccountException("该账号已被封停,请联系管理员!");
        }else{
            user.setLastLoginTime(new Date());
            userMapper.updateByPrimaryKey(user);
        }

        return new SimpleAuthenticationInfo(user, password, getName());
    }
}
