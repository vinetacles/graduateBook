package com.egroup.login.token.util;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.egroup.dynamoDB.service.SaveService;
import com.egroup.login.dynamoDB.entity.LoginToken;
import com.egroup.login.dynamoDB.service.LoginTokenService;
import com.egroup.login.util.Jwt;
import com.egroup.login.util.TokenState;
import com.egroup.util.AttributeCheck;
import com.egroup.util.CookieUtil;
import com.egroup.util.UUIDGenerator;

public class LoginTokenUtil {
	// init variable
	private final int loginTokenExt = 1000 * 60 * 60 * 24;
	private LoginToken loginToken;
	private HttpServletResponse response;
	private HttpServletRequest request;

	public LoginTokenUtil(LoginToken loginToken) {
		this.loginToken = loginToken;
		this.response = loginToken.getResponse();
		this.request = loginToken.getRequest();
	}
	
	public LoginToken valid() {
		// init service
		final LoginTokenService loginTokenService = new LoginTokenService();
		final Jwt jwt = new Jwt();
		// init func
		final AttributeCheck attributeCheck = new AttributeCheck();
		final CookieUtil cookieUtil = new CookieUtil();
		// init variable
		// 若有tokenID
		if (attributeCheck.stringsNotNull(loginToken.getLoginId(),loginToken.getLoginTokenId())) {
			// 取得資料庫Token資料
			loginToken = loginTokenService.get(loginToken);
			// 若資料庫有Token資料
			if (loginToken != null) {
				// 判斷是否有手機驗證，有的話檢查是否已經驗證
				Map<String, Object> validToken = jwt.validToken(loginToken.getLoginTokenId(), loginToken.getLoginTokenKey());
				// 驗證Token是否合法
				if (validToken.get("state").equals(TokenState.VALID.toString())) {
					loginToken.setTokenMsg("Token驗證成功");
					// 儲存cookie
					cookieUtil.setLoginTokenCookie(loginToken, response);
				} else if (validToken.get("state").equals(TokenState.EXPIRED.toString())) {
					// init service
					final SaveService saveService = new SaveService();
					// init func
					final String date = ZonedDateTime.now(ZoneOffset.UTC).toString();
					final UUIDGenerator uuidGenerator = new UUIDGenerator();						
					
					// 設定token狀態
					loginToken.setTokenMsg("Token過期，更新成功");
					
					// 建立新的token
					final Map<String, Object> payload = new HashMap<String, Object>();
					payload.put("uid", loginToken.getLoginId()); // 用户ID
					payload.put("iat", System.currentTimeMillis());
					payload.put("ext", System.currentTimeMillis() + loginTokenExt);

					final String key = uuidGenerator.getUUID();
					final String createDate = date + uuidGenerator.getUUID();
					
					LoginToken getLoginToken;
					String errorMsg;
					while(true){
						getLoginToken = loginTokenService.get(loginToken);
						if(getLoginToken!=null){		
							//設定舊的tokenID和key
							getLoginToken.setLoginTokenId_previous(loginToken.getLoginTokenId());
							getLoginToken.setLoginTokenKey_previous(loginToken.getLoginTokenKey());									
							// 重新產生金鑰
							getLoginToken.setLoginTokenKey(key);
							getLoginToken.setLoginTokenId(jwt.createToken(payload, key));
							getLoginToken.setCreateDate(createDate);						
							// 更新loginToken
							errorMsg = saveService.save(getLoginToken);
							if(errorMsg==null){
								// 儲存cookie
								cookieUtil.setLoginTokenCookie(getLoginToken, response);	
								break;
							}
						}else{
							break;
						}
					}			
				} else {
					loginToken.setTokenMsg("Token不合法，請重新註冊");
					cookieUtil.cleanTokenCookie(request, response);
				}			
			} else {
				loginToken = new LoginToken();
				loginToken.setTokenMsg("查無此Token，請重新註冊");
				cookieUtil.cleanTokenCookie(request, response);
			}
		} else {
			loginToken.setTokenMsg("Token驗證失敗");
			cookieUtil.cleanTokenCookie(request, response);
		}
		//清除key、createDate
		loginToken.setLoginTokenKey(null);
		loginToken.setCreateDate(null);
		loginToken.setLoginTokenKey_previous(null);
		loginToken.setLoginTokenId_previous(null);
		return loginToken;
	}
	
	public LoginToken valid_prevois() {
		// init service
		final LoginTokenService loginTokenService = new LoginTokenService();
		final Jwt jwt = new Jwt();
		// init func
		final AttributeCheck attributeCheck = new AttributeCheck();
		final CookieUtil cookieUtil = new CookieUtil();
		// init variable
		// 若有tokenID
		if (attributeCheck.stringsNotNull(loginToken.getLoginId(),loginToken.getLoginTokenId())) {
			// 取得資料庫Token資料
			loginToken = loginTokenService.get(loginToken);
			// 若資料庫有Token資料
			if (loginToken != null) {
				Map<String, Object> validToken = jwt.validToken(loginToken.getLoginTokenId(), loginToken.getLoginTokenKey());
				// 驗證Token是否合法
				if (validToken.get("state").equals(TokenState.VALID.toString())||validToken.get("state").equals(TokenState.EXPIRED.toString())){
					loginToken.setTokenMsg("Token驗證成功");
				}else {
					if(loginToken.getLoginTokenId_previous()!=null&&loginToken.getLoginTokenKey_previous()!=null){
						validToken = jwt.validToken(loginToken.getLoginTokenId_previous(), loginToken.getLoginTokenKey_previous());
						// 驗證Token是否合法
						if (validToken.get("state").equals(TokenState.VALID.toString())||validToken.get("state").equals(TokenState.EXPIRED.toString())){
							loginToken.setTokenMsg("Token驗證成功");
						}else {
							loginToken.setTokenMsg("Token不合法，請重新註冊");
							cookieUtil.cleanTokenCookie(request, response);
						}
					}else{					
						loginToken.setTokenMsg("Token不合法，請重新註冊");
						cookieUtil.cleanTokenCookie(request, response);
					}					
				}
			} else {
				loginToken = new LoginToken();
				loginToken.setTokenMsg("查無此Token，請重新註冊");
				cookieUtil.cleanTokenCookie(request, response);
			}
		} else {
			loginToken.setTokenMsg("Token驗證失敗");
			cookieUtil.cleanTokenCookie(request, response);
		}
		// remove Confidential info
		loginToken.removeConfidential();
		return loginToken;
	}
}