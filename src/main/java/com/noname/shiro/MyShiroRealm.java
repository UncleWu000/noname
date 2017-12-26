package com.noname.shiro;

import com.noname.entity.Admin;
import com.noname.entity.User;
import com.noname.mapper.UserMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class MyShiroRealm  extends AuthorizingRealm{

    @Resource
    UserMapper userMapper;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String name = token.getUsername();
        String password = String.valueOf(token.getPassword());

//        //访问一次，计数一次
//        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
//        opsForValue.increment(SHIRO_LOGIN_COUNT+name, 1);
//        //计数大于5时，设置用户被锁定一小时
//        if(Integer.parseInt(opsForValue.get(SHIRO_LOGIN_COUNT+name))>=5){
//            opsForValue.set(SHIRO_IS_LOCK+name, "LOCK");
//            stringRedisTemplate.expire(SHIRO_IS_LOCK+name, 1, TimeUnit.HOURS);
//        }

        User user = null;
        user.setNickname(name);
        user.setPswd(password);
        List<User> userList = userMapper.select(user);

        if(userList.size()>0){
            user = userList.get(0);
        }

        if(user.getId() != null){
            throw new AccountException("账号或密码不正确!");
        }else if("0".equals(user.getStatus())){
            throw new DisabledAccountException("该账号暂时被禁用!");
        }else{
            user.setTime(new Date());
            userMapper.updateByPrimaryKey(user);
            System.out.println(user + new Date().toString() + " 登陆成功!");
        }
        return new SimpleAuthenticationInfo(user, user.getPswd(), user.getNickname());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("权限认证方法");
        User user = (User)principalCollection.getPrimaryPrincipal();
        Long userId = user.getId();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //根据用户ID查询角色（role），放入到Authorization里。
		/*Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", userId);
		List<SysRole> roleList = sysRoleService.selectByMap(map);
		Set<String> roleSet = new HashSet<String>();
		for(SysRole role : roleList){
			roleSet.add(role.getType());
		}*/
        Set<String> roleSet = new HashSet<>();
        roleSet.add("1001");
        info.setRoles(roleSet);

        //根据用户ID查询权限（permission），放入到Authorization里。
		/*List<SysPermission> permissionList = sysPermissionService.selectByMap(map);
		Set<String> permissionSet = new HashSet<String>();
		for(SysPermission Permission : permissionList){
			permissionSet.add(Permission.getName());
		}*/
        Set<String> permissionSet = new HashSet<String>();
        permissionSet.add("权限添加");
        permissionSet.add("权限删除");
        info.setStringPermissions(permissionSet);
        return info;
    }
}
