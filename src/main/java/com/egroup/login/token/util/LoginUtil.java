package com.egroup.login.token.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.egroup.login.dynamoDB.entity.LoginToken;

/**
 * 登入Token驗證模組
 * @author Daniel
 *
 */
public class LoginUtil {
	private HttpServletRequest request;
	private String tokenId;
	private String loginId;
	private HttpServletResponse response;
	
	public LoginUtil(){}
	
	public LoginUtil(String tokenId){
		this.tokenId = tokenId;
	}
	
	public LoginUtil(HttpServletRequest request,HttpServletResponse response,String tokenId,String loginId){
		this.response = response;
		this.loginId = loginId;
		this.tokenId = tokenId;
		this.request = request;
	}
	
	public LoginUtil(HttpServletRequest request) {
		this.request = request;
	}
	
	/**
	 * 客戶登入驗證模組
	 * @return 回傳會員資料
	 */
	public LoginToken checkLogin(){
		LoginToken loginToken = new LoginToken();
		loginToken.setLoginTokenId(tokenId);
		loginToken.setLoginId(loginId);
		loginToken.setResponse(response);
		loginToken.setRequest(request);
		final LoginTokenUtil loginTokenUtil = new LoginTokenUtil(loginToken);
		return loginToken = loginTokenUtil.valid();
	}
	
	
	/**
	 * 客戶登入驗證模組
	 * @return 回傳會員資料
	 */
	public LoginToken checkLogin_previous(){
		LoginToken loginToken = new LoginToken();
		loginToken.setLoginTokenId(tokenId);
		loginToken.setLoginId(loginId);
		loginToken.setResponse(response);
		loginToken.setRequest(request);
		final LoginTokenUtil loginTokenUtil = new LoginTokenUtil(loginToken);
		return loginToken = loginTokenUtil.valid_prevois();
	}
	
	public boolean controllerValid(LoginToken loginToken){
		if(loginToken.getTokenMsg()!=null&&(loginToken.getTokenMsg().equals("Token驗證成功")
				||loginToken.getTokenMsg().equals("Token過期，更新成功"))){
			System.out.println("loginToken.getTokenMsg()="+loginToken.getTokenMsg());
			return true;
		}
		return false;
	}
	
	public boolean smsValid(LoginToken loginToken){
		if(loginToken.getTokenMsg()!=null&&(loginToken.getTokenMsg().equals("Token驗證成功")||loginToken.getTokenMsg().equals("Token過期，更新成功")
			||loginToken.getTokenMsg().equals("還未進行手機驗證，請進行手機驗證"))){
			System.out.println("loginToken.getTokenMsg()="+loginToken.getTokenMsg());
			return true;
		}
		return false;
	}
}
