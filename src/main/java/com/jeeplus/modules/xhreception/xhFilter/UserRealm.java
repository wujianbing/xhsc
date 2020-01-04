package com.jeeplus.modules.xhreception.xhFilter;

import java.io.Serializable;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeeplus.modules.sys.entity.User;
import com.jeeplus.modules.sys.security.UsernamePasswordToken;
import com.jeeplus.modules.sys.utils.UserUtils;
import com.jeeplus.modules.xhuser.entity.XhUser;
import com.jeeplus.modules.xhuser.service.XhUserService;

@Service
public class UserRealm extends AuthorizingRealm {
	
	@Autowired
	XhUserService xhUserService;
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		XhUser xhUser = null;
		
		//转换自定义token
		UsernamePasswordToken uToken = (UsernamePasswordToken)token;
		//从token中获取xhuser信息
		String name = uToken.getUsername();
		//通过名字获取相关信息
		xhUser = xhUserService.getUserByname(name);
		
		if(xhUser == null)
            throw new UnknownAccountException("用户不存在！");
		 // 根据用户的情况，来构建AuthenticationInfo对象并返回，通常使用的实现类为SimpleAuthenticationInfo
        // 以下信息从数据库中获取
        // （1）principal：认证的实体信息，可以是username，也可以是数据表对应的用户的实体类对象
        Object principal1 = xhUser;
        // （2）credentials：密码
        Object credentials = xhUser.getPassword();
        // （3）realmName：当前realm对象的name，调用父类的getName()方法即可
        String realmName = getName();
        // （4）盐值：取用户信息中唯一的字段来生成盐值，避免由于两个用户原始密码相同，加密后的密码也相同
        ByteSource credentialsSalt = ByteSource.Util.bytes(name);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal1, credentials, credentialsSalt,
                realmName);
        return info;
	}

	
	/**
	 * 授权用户信息
	 */
	public static class Principal1 implements Serializable {

		private static final long serialVersionUID = 1L;
		
		private String id; // 编号
		private String loginName; // 登录名
		private String name; // 姓名
		private boolean mobileLogin; // 是否手机登录
		
//		private Map<String, Object> cacheMap;

		public Principal1(XhUser user, boolean mobileLogin) {
			this.id = user.getId();
			this.loginName = user.getUsername();
			this.name = user.getUsername();
			this.mobileLogin = mobileLogin;
		}

		public String getId() {
			return id;
		}

		public String getLoginName() {
			return loginName;
		}

		public String getName() {
			return name;
		}

		public boolean isMobileLogin() {
			return mobileLogin;
		}

//		@JsonIgnore
//		public Map<String, Object> getCacheMap() {
//			if (cacheMap==null){
//				cacheMap = new HashMap<String, Object>();
//			}
//			return cacheMap;
//		}

		/**
		 * 获取SESSIONID
		 */
		public String getSessionid() {
			try{
				return (String) UserUtils.getSession().getId();
			}catch (Exception e) {
				return "";
			}
		}
		
		@Override
		public String toString() {
			return id;
		}

	}
}
