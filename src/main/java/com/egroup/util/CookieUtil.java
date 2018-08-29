package com.egroup.util;

import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.egroup.login.dynamoDB.entity.LoginToken;
import com.google.gson.Gson;


public class CookieUtil {
	
	public void setLoginTokenCookie(LoginToken loginToken,HttpServletResponse response){
		//init func
		final Gson gson = new Gson();
		final AttributeCheck attributeCheck = new AttributeCheck();
		final Base64Util base64Util = new Base64Util();
		
		if(attributeCheck.objectNotNull(loginToken)){	
			if(attributeCheck.stringsNotNull(loginToken.getLoginId(),loginToken.getLoginId())){
				setCookie("tid", loginToken.getLoginId(), response);
				setCookie("lid", loginToken.getLoginId(), response);
				
				final LoginToken seLoginToken = new LoginToken();
				seLoginToken.setLoginName(loginToken.getLoginName());
				seLoginToken.setLoginAccount(loginToken.getLoginAccount());
				seLoginToken.setLoginStatus(loginToken.getLoginStatus());
				seLoginToken.setLoginPhone(loginToken.getLoginPhone());
				setCookie("m_info", base64Util.encode(gson.toJson(seLoginToken)), response);
			}
		}
	}
	
	public void setCookie(String name,String value,HttpServletResponse response){
		// save Cookie
		final Cookie cookie_tid = new Cookie(name, value);						
		cookie_tid.setMaxAge(60 * 60 * 24); // 24HR
		cookie_tid.setPath("/");
		//	cookie_tid.setSecure(true);
		response.addCookie(cookie_tid);
	}
	
	
	/**
	 * Get Cookie
	 * @param request
	 * @param cookieName
	 * @return
	 */
	public String getCookie(HttpServletRequest request,String cookieName){
		final Cookie[] cookies = request.getCookies();
		final HashMap<String, String> cookieHashmap = new HashMap<>();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				cookieHashmap.put(cookies[i].getName(), cookies[i].getValue());
			}
			return cookieHashmap.get(cookieName);
		}
		return null;
	}
	
	/**
	 * Get Cookie Hashmap
	 * @param request
	 * @param cookieName
	 * @return
	 */
	public HashMap<String, String> getCookieHashMap(HttpServletRequest request){
		final Cookie[] cookies = request.getCookies();
		final HashMap<String, String> cookieHashmap = new HashMap<>();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				cookieHashmap.put(cookies[i].getName(), cookies[i].getValue());
			}
			return cookieHashmap;
		}
		return null;
	}
	
	/**
	 * Clean Cookie
	 * @param request
	 * @param response
	 * @return
	 */
	public boolean cleanTokenCookie(HttpServletRequest request,HttpServletResponse response){
		boolean flag;
		flag = cleanCookie(request, response, "tid");
		flag = cleanCookie(request, response, "lid");
//		flag = cleanCookie(request, response, "m_info");
		return flag;
	}
	
	
	//清除Cookie
	public boolean cleanCookie(HttpServletRequest request,HttpServletResponse response,String cookieName){
		final Cookie[] cookies = request.getCookies();
		if(cookies!=null){
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals(cookieName)){
					cookies[i].setMaxAge(0);
					cookies[i].setPath("/");
					response.addCookie(cookies[i]);
					return true;
				}
			}
		}
		return false;
	}
}
