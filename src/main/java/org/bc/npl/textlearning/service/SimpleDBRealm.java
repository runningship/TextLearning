package org.bc.npl.textlearning.service;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 *
 */
public class SimpleDBRealm extends AuthorizingRealm {

    /**
     * 为当前登录的Subject授予角色和权限
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
        return simpleAuthorInfo;
    }

    /**
     * 验证当前登录的Subject
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        //查数据库
//        OpsUser vo = new OpsUser();
//        vo.setUsername(token.getUsername());
//        vo.setPassword(String.valueOf(token.getPassword()));
//        OpsUser userPo = (OpsUser) baseService.getByExample(vo);
//        if (userPo == null) {
//            throw new BusinessException("账号密码不正确");
//        }
//        SecurityUtils.getSubject().getSession().setAttribute(EasyOpsConst.Session.Session_User_Key, userPo);
        AuthenticationInfo authcInfo = new SimpleAuthenticationInfo("", token.getPassword(), this.getName());
        return authcInfo;
    }
}
