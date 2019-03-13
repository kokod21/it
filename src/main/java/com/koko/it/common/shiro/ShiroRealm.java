package com.koko.it.common.shiro;

import com.koko.it.common.constants.Constants;
import com.koko.it.entity.User;
import com.koko.it.service.PermissionService;
import com.koko.it.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
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
        if(Constants.DEFAULT_NAME.equals(username)){
            simpleAuthorizationInfo.addRole("*");
            simpleAuthorizationInfo.addStringPermission("*");
            return simpleAuthorizationInfo;
        } else {
            User user = userService.findByUserName(username);
            if (user != null) {
                List<Map<String, Object>> lists = permissionService.getPermissionByUserId(user.getId());
                for (Map<String, Object> list : lists) {
                    //配置权限信息
                    simpleAuthorizationInfo.addStringPermission(list.get("code").toString());
                }
                return simpleAuthorizationInfo;
            }
        }
        return null;
    }


    /**
     * 验证用户身份,如果验证失败，返回null或者异常（带返回message），跳转到login的post链接
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = authenticationToken.getPrincipal().toString();
        if(username.equals(Constants.DEFAULT_NAME)){
            return new SimpleAuthenticationInfo(Constants.DEFAULT_NAME, DigestUtils.md5Hex(Constants.DEFAULT_PASSWORD), getName());
        } else {
            User user = userService.findByUserName(username);
            String salt = username+ String.valueOf(user.getPassword());
            return new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), ByteSource.Util.bytes(salt), getName());
        }
    }
}
