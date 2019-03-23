package com.koko.it.common.shiro;

import com.koko.it.entity.User;
import com.koko.it.service.PermissionService;
import com.koko.it.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class ShiroRealm extends AuthorizingRealm{
    @Autowired
    UserService userService;
    @Autowired
    PermissionService permissionService;

    /**
     * 验证用户权限
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        String username = principalCollection.getPrimaryPrincipal().toString();
        User user = userService.findByUserName(username);
        if (user != null) {
            List<Map<String, Object>> lists = permissionService.getPermissionByUserId(user.getId());
            for (Map<String, Object> list : lists) {
                //配置权限信息
                simpleAuthorizationInfo.addStringPermission(list.get("code").toString());
            }
            return simpleAuthorizationInfo;
        }
        return null;
    }


    /**
     * 验证用户身份,如果验证失败，返回null或者异常（带返回message），跳转到login的post链接
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String username = usernamePasswordToken.getUsername();
        String password = new String(usernamePasswordToken.getPassword());
        User user = userService.findByUserNameAndPasswordAndIsDel(username, password, 0);
        String salt = username+ String.valueOf(user.getPassword());
        return new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), ByteSource.Util.bytes(salt), getName());
    }
}
