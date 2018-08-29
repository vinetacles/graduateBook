package com.egroup.login.controller.rest;

import java.security.NoSuchAlgorithmException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

import com.egroup.amazon.cloudwatch.logback.entity.LogReport;
import com.egroup.dynamoDB.service.DeleteService;
import com.egroup.dynamoDB.service.SaveService;
import com.egroup.login.dynamoDB.entity.Login;
import com.egroup.login.dynamoDB.entity.LoginToken;
import com.egroup.login.dynamoDB.service.LoginService;
import com.egroup.login.dynamoDB.service.LoginTokenService;
import com.egroup.login.util.Jwt;
import com.egroup.util.AttributeCheck;
import com.egroup.util.Base64Util;
import com.egroup.util.CookieUtil;
import com.egroup.util.SecurityMD5;
import com.egroup.util.UUIDGenerator;
import com.egroup.util.entity.WebResponse;
import com.google.gson.Gson;

@Path("/")
@Controller
public class LoginRestController {
	static final Logger LOGGER = LoggerFactory.getLogger(LoginRestController.class);
	static final ApplicationContext CONTEXT_RDS = new ClassPathXmlApplicationContext("spring-module-rds.xml");
	private final int LOGIN_TOKEN_EXT = 1000 * 60 * 60 * 24;

	/**
	 * @param login
	 * * account
	 * * password
	 * @return
	 */
	@POST
	@Path("/checkLogin")
	@Consumes("application/json")
	public Response checkLogin(Login login, @Context HttpServletRequest request,
			@Context HttpServletResponse response) {
		// init func
		final Gson gson = new Gson();
		final AttributeCheck attributeCheck = new AttributeCheck();
		final CookieUtil cookieUtil = new CookieUtil();
		final Base64Util base64Util = new Base64Util();
		// init variable
		final String logReportBase64String = cookieUtil.getCookie(request, "logReport");
		final String logReportString = base64Util.decode(logReportBase64String);
		LogReport logReport = gson.fromJson(logReportString, LogReport.class);
		final WebResponse webResponse = new WebResponse();
		if (!attributeCheck.objectNotNull(logReport)) {
			logReport = new LogReport();
		}
		// check attribute
		if (attributeCheck.objectNotNull(login)&& attributeCheck.stringsNotNull(login.getAccount(), login.getPassword())) {
			// init service
			final LoginService loginService = new LoginService();
			// init func
			final SecurityMD5 securityMD5 = new SecurityMD5();
			// init variable
			String passwordInput = login.getPassword();
			// checkt account
			login = loginService.get(login);
			if (attributeCheck.objectNotNull(login)) {
				// password md5 encrypt
				try {
					passwordInput = securityMD5.encryptWords(passwordInput);
				} catch (NoSuchAlgorithmException e) {
					// LogReport
					logReport.setMessage("?��?��驗�??-密碼??��?�失???");
					logReport.setFunction("login.setPassword(securityMD5.encryptWords(login.getPassword()))");
					logReport.setAttributes(login.getPassword());
					LOGGER.error(gson.toJson(logReport), e);
				}
				// validate password
				if (login.getPassword().equals(passwordInput)) {
					// init service
					final SaveService saveService = new SaveService();
					final DeleteService deleteService = new DeleteService();
					final LoginTokenService loginTokenService = new LoginTokenService();
					// init func
					final Jwt jwt = new Jwt();
					final String date = ZonedDateTime.now(ZoneOffset.UTC).toString();
					final UUIDGenerator uuidGenerator = new UUIDGenerator();// UUID?��??�器
					// init variable
					Map<String, Object> payload = new HashMap<String, Object>();

					// set login date
					login.setDate(date);

					// set login token data
					LoginToken loginToken = new LoginToken();
					loginToken.setLoginAccount(login.getAccount());
					loginToken = loginTokenService.get_ByAccount(loginToken);
					// if has loginToken
					if (loginToken != null) {
						// delete loginToken
						deleteService.delete(loginToken);
					} else {
						loginToken = new LoginToken();
						loginToken.setLoginId(login.getLoginId());
						loginToken.setLoginName(login.getName());
						loginToken.setLoginAccount(login.getAccount());
					}

					// set loginToken message
					payload = new HashMap<String, Object>();
					payload.put("uid", login.getLoginId()); // loginID
					payload.put("iat", System.currentTimeMillis());
					payload.put("ext", System.currentTimeMillis() + LOGIN_TOKEN_EXT); // 24HR

					// set loginToken data
					loginToken.setLoginTokenKey(uuidGenerator.getUUID());
					loginToken.setLoginTokenId(jwt.createToken(payload, loginToken.getLoginTokenKey()));
					loginToken.setCreateDate(date + uuidGenerator.getUUID());

					// save login and loginToken data
					saveService.batchSave(login, loginToken);
					loginToken.removeConfidential();

					// save loginToken Cookie
					cookieUtil.setLoginTokenCookie(loginToken, response);

					// Set Response
					webResponse.setData(loginToken);
					webResponse.OK();
					// remove the loginToken message
					payload.clear();
					// write user behavior
					logReport = new LogReport();
					logReport.setFunction("POST rest/login/checkLogin");
					logReport.setSubject(loginToken);
					logReport.setVerb("checkLogin");
					LOGGER.info(gson.toJson(logReport));
				} else {
					// 密碼輸入?���?
					webResponse.UNAUTHORIZED();
					webResponse.getError().setMessage("Incorrect account or password");
					webResponse.setData(webResponse.getError());
				}
			} else {
				// ?��?��此帳???
				webResponse.NOT_FOUND();
				webResponse.getError().setMessage("account is not found");
				webResponse.setData(webResponse.getError());
			}
		} else {
			webResponse.UNPROCESSABLE_ENTITY();
			webResponse.getError().setMessage("account, password required");
			webResponse.setData(webResponse.getError());
		}
		return Response.status(webResponse.getStatusCode()).entity(webResponse.getData()).build();
	}

	/**
	 * Register LoginToken and memberInfo
	 * @author Daniel
	 *
	 * @param login
	 * @param request
	 * @param response
	 * @return
	 */
	@POST
	@Path("/register")
	@Consumes("application/json")
	public Response register(Login login,@Context HttpServletRequest request,@Context HttpServletResponse response) {
		// init func
		final AttributeCheck attributeCheck = new AttributeCheck();
		final Gson gson = new Gson();
		// init variable
		final WebResponse webResponse = new WebResponse();
		if (attributeCheck.objectNotNull(login) && attributeCheck.stringsNotNull(login.getAccount(), login.getPassword(), login.getName())) {
			// init service
			LoginService loginService = new LoginService();
			// check whether have this account
			Login getLogin = loginService.get(login);
			if (getLogin == null) {
				// if didn't have this account
				// init service
				final SaveService saveService = new SaveService();
				// init func
				final SecurityMD5 securityMD5 = new SecurityMD5();
				final UUIDGenerator uuidGenerator = new UUIDGenerator();
				final CookieUtil cookieUtil = new CookieUtil();
				final Base64Util base64Util = new Base64Util();
				// init variable
				final String logReportBase64String = cookieUtil.getCookie(request, "logReport");
				final String logReportString = base64Util.decode(logReportBase64String);

				LogReport logReport = gson.fromJson(logReportString, LogReport.class);
				if (!attributeCheck.objectNotNull(logReport)) {
					logReport = new LogReport();
				}
				final String password = login.getPassword();
				String passwordEncrypt = null;
				try {
					passwordEncrypt = securityMD5.encryptWords(password);
				} catch (NoSuchAlgorithmException e) {
					// LogReport
					logReport.setMessage("?��?��驗�??-密碼??��?�失???");
					logReport.setFunction("login.setPassword(securityMD5.encryptWords(login.getPassword()))");
					logReport.setAttributes(login.getPassword());
					LOGGER.error(gson.toJson(logReport), e);
				}

				// Save Login Info
				login.setPassword(passwordEncrypt);
				login.setLoginId(uuidGenerator.getUUID());
				saveService.save(login);
				// init service
				final DeleteService deleteService = new DeleteService();
				final LoginTokenService loginTokenService = new LoginTokenService();
				// init func
				final Jwt jwt = new Jwt();
				final String date = ZonedDateTime.now(ZoneOffset.UTC).toString();
				// init variable
				Map<String, Object> payload = new HashMap<String, Object>();

				// set login date
				login.setDate(date);

				// set login token data
				LoginToken loginToken = new LoginToken();
				loginToken.setLoginAccount(login.getAccount());
				loginToken = loginTokenService.get_ByAccount(loginToken);
				// if has loginToken
				if (loginToken != null) {
					// delete loginToken
					deleteService.delete(loginToken);
				} else {
					loginToken = new LoginToken();
					loginToken.setLoginId(login.getLoginId());
					loginToken.setLoginName(login.getName());
					loginToken.setLoginAccount(login.getAccount());
				}

				// set loginToken message
				payload = new HashMap<String, Object>();
				payload.put("uid", login.getLoginId()); // ?��?��ID
				payload.put("iat", System.currentTimeMillis());
				payload.put("ext", System.currentTimeMillis() + LOGIN_TOKEN_EXT); // 24HR

				// set loginToken data
				loginToken.setLoginTokenKey(uuidGenerator.getUUID());
				loginToken.setLoginTokenId(jwt.createToken(payload, loginToken.getLoginTokenKey()));
				loginToken.setCreateDate(date + uuidGenerator.getUUID());

				// save login and loginToken data
				saveService.batchSave(login, loginToken);
				loginToken.removeConfidential();

				// save loginToken Cookie
				cookieUtil.setLoginTokenCookie(loginToken, response);
				
				login.setPassword(null);
				webResponse.setData(login);
				webResponse.OK();
			} else {
				webResponse.CONFLICT();
				webResponse.getError().setMessage("account has already been taken");
				webResponse.setData(webResponse.getError());
			}
		} else {
			webResponse.UNPROCESSABLE_ENTITY();
			webResponse.getError().setMessage("account, password, name required");
			webResponse.setData(webResponse.getError());
		}
		return Response.status(webResponse.getStatusCode()).entity(webResponse.getData()).build();
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@POST
	@Path("/logout")
	@Consumes("application/json")
	public Response logout(@Context HttpServletRequest request, @Context HttpServletResponse response) {
		// 清除cookie
		final CookieUtil cookieUtil = new CookieUtil();
		cookieUtil.cleanTokenCookie(request, response);
		// init variable
		final WebResponse webResponse = new WebResponse();
		webResponse.OK();
		return Response.status(webResponse.getStatusCode()).build();
	}
}
