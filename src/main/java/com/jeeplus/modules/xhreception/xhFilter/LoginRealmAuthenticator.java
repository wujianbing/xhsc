package com.jeeplus.modules.xhreception.xhFilter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

import com.jeeplus.modules.sys.security.UsernamePasswordToken;

/**
* 自定义认证器
* 
* @author Sunny
*/
public class LoginRealmAuthenticator extends ModularRealmAuthenticator {

/**
 * 根据用户类型判断使用哪个Realm
 */
@Override
protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken)
		throws AuthenticationException {
	super.assertRealmsConfigured();
	// 使用自定义Token
	UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
	// 判断用户类型
	 String loginType = token.getLoginType();
     // 所有Realm
     Collection<Realm> realms = getRealms();
     // 登录类型对应的所有Realm
     Collection<Realm> typeRealms = new ArrayList<>();
     for (Realm r : realms) {
         if (r.getName().contains(loginType))
             typeRealms.add(r);
     }

     // 判断是单Realm还是多Realm
     if (typeRealms.size() == 1)
         return doSingleRealmAuthentication(typeRealms.iterator().next(), token);
     else
         return doMultiRealmAuthentication(typeRealms, token);
 }

}

