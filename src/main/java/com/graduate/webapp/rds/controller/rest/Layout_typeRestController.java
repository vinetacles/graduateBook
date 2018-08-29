package com.graduate.webapp.rds.controller.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.jboss.resteasy.annotations.GZIP;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

import com.graduate.webapp.rds.entity.Layout_type;
import com.graduate.webapp.rds.dao.Layout_typeDAO;
import com.egroup.util.AttributeCheck;
import com.egroup.util.CookieUtil;
import com.egroup.util.entity.WebResponse;
import com.egroup.util.SqlUtil;
import com.egroup.util.entity.LikeGenerator;
import com.egroup.util.entity.LimitGenerator;
import com.egroup.util.entity.OrderGenerator;
import com.egroup.util.entity.EqualGenerator;
import com.egroup.login.token.util.LoginUtil;
import com.egroup.login.dynamoDB.entity.LoginToken;

@Path("/layout_type")
@Controller
public class Layout_typeRestController {
private static Logger LOGGER = LoggerFactory.getLogger(Layout_typeRestController.class);
final ApplicationContext context = new ClassPathXmlApplicationContext("spring-module-rds.xml");


@POST
@Consumes(MediaType.APPLICATION_JSON)
public Response insert(Layout_type layout_type, @Context HttpServletRequest request,@Context HttpServletResponse response) {
// init DAO
final Layout_typeDAO layout_typeDAO = (Layout_typeDAO) context.getBean("layout_typeDAO");
// init func
final CookieUtil cookieUtil = new CookieUtil();
// init variable
final WebResponse webResponse = new WebResponse();
final String loginID = cookieUtil.getCookie(request, "lid");
final String tokenID = cookieUtil.getCookie(request, "tid");
// Check login token
final LoginUtil loginUtil = new LoginUtil(request, response, loginID, tokenID);
final LoginToken loginToken = loginUtil.checkLogin();
// verify loginToken
if (loginUtil.controllerValid(loginToken)) {
layout_typeDAO.insert(layout_type);
webResponse.setData(layout_type);
webResponse.OK();
}else{
webResponse.getError().setMessage("Authentication failed");
webResponse.setData(webResponse.getError());
webResponse.UNPROCESSABLE_ENTITY();
}
return Response.status(webResponse.getStatusCode()).entity(webResponse.getData()).build();
}


@PATCH
@Consumes(MediaType.APPLICATION_JSON)
public Response udpate(Layout_type layout_type, @Context HttpServletRequest request,@Context HttpServletResponse response) {
// init DAO
final Layout_typeDAO layout_typeDAO = (Layout_typeDAO) context.getBean("layout_typeDAO");
// init func
final CookieUtil cookieUtil = new CookieUtil();
// init variable
final WebResponse webResponse = new WebResponse();
final String loginID = cookieUtil.getCookie(request, "lid");
final String tokenID = cookieUtil.getCookie(request, "tid");
// Check login token
final LoginUtil loginUtil = new LoginUtil(request, response, loginID, tokenID);
final LoginToken loginToken = loginUtil.checkLogin();
// verify loginToken
if (loginUtil.controllerValid(loginToken)) {
final Layout_type oldLayout_type = layout_typeDAO.get(layout_type);
layout_typeDAO.update(layout_type,oldLayout_type);
webResponse.setData(layout_type);
webResponse.OK();
}else{
webResponse.getError().setMessage("Authentication failed");
webResponse.setData(webResponse.getError());
webResponse.UNPROCESSABLE_ENTITY();
}
return Response.status(webResponse.getStatusCode()).entity(webResponse.getData()).build();
}


@DELETE
@Path("/{layoutTypeId}")
public Response delete(@PathParam("layoutTypeId") Integer layoutTypeId,@Context HttpServletRequest request,@Context HttpServletResponse response) {
// init DAO
final Layout_typeDAO layout_typeDAO = (Layout_typeDAO) context.getBean("layout_typeDAO");
// init func
final CookieUtil cookieUtil = new CookieUtil();
// init variable
final WebResponse webResponse = new WebResponse();
final String loginID = cookieUtil.getCookie(request, "lid");
final String tokenID = cookieUtil.getCookie(request, "tid");
// Check login token
final LoginUtil loginUtil = new LoginUtil(request, response, loginID, tokenID);
final LoginToken loginToken = loginUtil.checkLogin();
// verify loginToken
if (loginUtil.controllerValid(loginToken)) {
final Layout_type layout_type = new Layout_type();
layout_type.setLayoutTypeId(layoutTypeId);
layout_typeDAO.delete(layout_type);
webResponse.setData(layout_type);
webResponse.OK();
}else{
webResponse.getError().setMessage("Authentication failed");
webResponse.setData(webResponse.getError());
webResponse.UNPROCESSABLE_ENTITY();
}
return Response.status(webResponse.getStatusCode()).entity(webResponse.getData()).build();
}


@GET
@GZIP
@Path("/{layoutTypeId}")
public Response get(@PathParam("layoutTypeId") Integer layoutTypeId,@Context HttpServletRequest request,@Context HttpServletResponse response) {
// init DAO
final Layout_typeDAO layout_typeDAO = (Layout_typeDAO) context.getBean("layout_typeDAO");
// init func
final AttributeCheck attributeCheck = new AttributeCheck();
final CookieUtil cookieUtil = new CookieUtil();
// init variable
final WebResponse webResponse = new WebResponse();
final String loginID = cookieUtil.getCookie(request, "lid");
final String tokenID = cookieUtil.getCookie(request, "tid");
// Check login token
final LoginUtil loginUtil = new LoginUtil(request, response, loginID, tokenID);
final LoginToken loginToken = loginUtil.checkLogin();
// verify loginToken
if (loginUtil.controllerValid(loginToken)) {
Layout_type layout_type = new Layout_type();
layout_type.setLayoutTypeId(layoutTypeId);
layout_type = layout_typeDAO.get(layout_type);
if(layout_type != null){
webResponse.setData(layout_type);
webResponse.OK();
}else{
webResponse.NOT_FOUND();
webResponse.getError().setMessage("Data Not Found");
}
}else{
webResponse.getError().setMessage("Authentication failed");
webResponse.setData(webResponse.getError());
webResponse.UNPROCESSABLE_ENTITY();
}
return Response.status(webResponse.getStatusCode()).entity(webResponse.getData()).build();
}


@GET
@Path("/list")
@GZIP
public Response list(@DefaultValue("0") @QueryParam("offset") Integer offset,@QueryParam("search")String search,@DefaultValue("10") @QueryParam("limit")Integer limit,@Context HttpServletRequest request, @Context HttpServletResponse response) {
// init DAO
final Layout_typeDAO layout_typeDAO = (Layout_typeDAO) context.getBean("layout_typeDAO");
// init func
final AttributeCheck attributeCheck = new AttributeCheck();
final CookieUtil cookieUtil = new CookieUtil();
// init variable
final WebResponse webResponse = new WebResponse();
final String loginID = cookieUtil.getCookie(request, "lid");
final String tokenID = cookieUtil.getCookie(request, "tid");
// Check login token
final LoginUtil loginUtil = new LoginUtil(request, response, loginID, tokenID);
final LoginToken loginToken = loginUtil.checkLogin();
// verify loginToken
if (loginUtil.controllerValid(loginToken)) {
// init variable
final SqlUtil sqlUtil = new SqlUtil();
// init limitSQL
sqlUtil.getLimitGenerator().setOffset(offset);
sqlUtil.getLimitGenerator().setLimit(limit);
// init likeSQL
sqlUtil.getWhereGenerator().getLikeGenerator().getLikeFieldList().add("");
sqlUtil.getWhereGenerator().getLikeGenerator().setLike(search);
// init orderSQL
sqlUtil.getOrderGenerator().setOrder("");
sqlUtil.getOrderGenerator().setAsc(false);
// init equalSQL
// sqlUtil.getWhereGenerator().getEqualGenerator().setEqualHashMap("a.organizationId", organizationId);

// Get List By SqlUtil
List<Layout_type> layout_typeList = layout_typeDAO.getList(sqlUtil);
webResponse.setData(layout_typeList);
webResponse.OK();
}else{
webResponse.getError().setMessage("Authentication failed");
webResponse.setData(webResponse.getError());
webResponse.UNPROCESSABLE_ENTITY();
}
return Response.status(webResponse.getStatusCode()).entity(webResponse.getData()).build();
}


@GET
@Path("/list")
@GZIP
public Response listCount(@QueryParam("search")String search,@Context HttpServletRequest request, @Context HttpServletResponse response) {
// init DAO
final Layout_typeDAO layout_typeDAO = (Layout_typeDAO) context.getBean("layout_typeDAO");
// init func
final CookieUtil cookieUtil = new CookieUtil();
// init variable
final WebResponse webResponse = new WebResponse();
final String loginID = cookieUtil.getCookie(request, "lid");
final String tokenID = cookieUtil.getCookie(request, "tid");
// Check login token
final LoginUtil loginUtil = new LoginUtil(request, response, loginID, tokenID);
final LoginToken loginToken = loginUtil.checkLogin();
// verify loginToken
if (loginUtil.controllerValid(loginToken)) {
// init variable
final SqlUtil sqlUtil = new SqlUtil();
// init limitSQL
sqlUtil.getLimitGenerator().setOffset(offset);
sqlUtil.getLimitGenerator().setLimit(limit);
// init likeSQL
sqlUtil.getWhereGenerator().getLikeGenerator().getLikeFieldList().add("");
sqlUtil.getWhereGenerator().getLikeGenerator().setLike(search);
// init orderSQL
sqlUtil.getOrderGenerator().setOrder("");
sqlUtil.getOrderGenerator().setAsc(false);
// init equalSQL
// sqlUtil.getWhereGenerator().getEqualGenerator().setEqualHashMap("a.organizationId", organizationId);

// Get ListCount By SqlUtil
final int countTotal = layout_typeDAO.countTotal(sqlUtil);
webResponse.setData(countTotal);
webResponse.OK();
}else{
webResponse.getError().setMessage("Authentication failed");
webResponse.setData(webResponse.getError());
webResponse.UNPROCESSABLE_ENTITY();
}
return Response.status(webResponse.getStatusCode()).entity(webResponse.getData()).build();
}


}
