package com.egroup.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.egroup.amazon.cloudwatch.logback.entity.LogReport;
import com.egroup.amazon.util.SESUtil;
import com.egroup.util.entity.SendEmail;
import com.google.gson.Gson;

public class EmailUtil {
	private static Logger logger = LoggerFactory.getLogger(EmailUtil.class);
	
	/**
	 * 
	 * @param htmlPath (e.g.html/verifyEmail.html)
	 * @param to 
	 * @param title
	 * @param request
	 * @return
	 */
	public boolean sendEmail(SendEmail sendEmail){
		// init func
		final SESUtil sesUtil = new SESUtil();
		// init variable	
		try {
			// 因為object to String 會包含[] ， 所以用replace把[]拿掉
			sesUtil.send(sendEmail.getTo(), sendEmail.getTitle(), sendEmail.getBody().replace("[", "").replace("]", ""),sendEmail.isHtml());	
		} catch (Exception e) {
			final LogReport logReport = new LogReport();
    		logReport.setMessage(sendEmail.getLogMessage()+"-寄送Email失敗");
    		logReport.setFunction("sesUtil.send(to, title, message)");
    		logReport.setAttributes(sendEmail.getTo(), sendEmail.getTitle(), sendEmail.getBody());
        	logger.error(new Gson().toJson(logReport), e);
			return false;
		}		
		return true;
	}
	
	/**
	 * 讀取html模組，替換內容
	 * @param sendEmail 寄件資訊
	 * @param replaceHashMap 替換內容
	 * @return String body
	 */
	public String getEmailBody(SendEmail sendEmail,HashMap<String, String> replaceHashMap){
		// init func
		final AttributeCheck attributeCheck = new AttributeCheck();
		//init  variable
		if(attributeCheck.stringsNotNull(sendEmail.getHtmlPath(),sendEmail.getLogMessage())){
			// init func
			final Gson gson = new Gson();
			final HtmlUtil htmlUtil = new HtmlUtil();
			
			// init variable
			final File htmlFile = new File(EmailUtil.class.getClassLoader().getResource("").getPath()+"/html/DailyReport.html");
			if(htmlFile.exists()){
				String body = "";
				try {			
					body = htmlUtil.htmlToString(htmlFile.getAbsolutePath());		
				} catch (FileNotFoundException e) {
					//LogReport
					final LogReport logReport = new LogReport();
		    		logReport.setMessage(sendEmail.getLogMessage() + "-製作Email失敗");
		    		logReport.setFunction("htmlUtil.htmlToString(request.getServletContext().getRealPath(htmlPath))");
		    		logReport.setAttributes(sendEmail.getHtmlPath());
		        	logger.error(gson.toJson(logReport), e);	
		        	return null;
				}
				
				if(replaceHashMap.entrySet()!=null&&replaceHashMap.entrySet().size()>0){
					String key;
					String value;
					for(Map.Entry<String, String> entry : replaceHashMap.entrySet()) {
						if(entry.getKey()!=null){
						    key = entry.getKey().toString();
						    value = entry.getValue()!=null?entry.getValue().toString():"";
						    body = body.replaceAll(key, value);
						}
						
					}
				}				
				return body;	
			}			
		}
		return null;	
	}
	
	/**
	 * Email 格式驗證
	 * @param email
	 * @return
	 */
	public boolean validateEmail(String email) {
		boolean isValid = true;
		try {
			final InternetAddress internetAddress = new InternetAddress(email);
			internetAddress.validate();
		} catch (AddressException e) {
			isValid = false;
			System.out.println("You are in catch block -- Exception Occurred for: " + email);
		}
		return isValid;
	}
	
	/**
	 * 
	 * @param sendEmail
	 * @return
	 */
	public boolean sendEamil_System(SendEmail sendEmail){
		final AttributeCheck attributeCheck = new AttributeCheck();
		List<String> emailList = new ArrayList<>();
		if(attributeCheck.stringsNotNull(sendEmail.getGroupName())){
			switch (sendEmail.getGroupName()){			
			case "manager":
				emailList.add("egroup.james@gmail.com");
				emailList.add("egroup.leonard@gmail.com");
				emailList.add("egroup.jerrylee@gmail.com");
				emailList.add("egroup.daniel@gmail.com");
				break;
			case "test":
				emailList.add("egroup.leonard@gmail.com");
				emailList.add("egroup.jerrylee@gmail.com");
				emailList.add("egroup.daniel@gmail.com");
				break;
			case "developer":
				emailList.add("egroup.daniel@gmail.com");
			}				
		}
				
		// init func
		final SESUtil sesUtil = new SESUtil();
		// init variable
		final ExecutorService executorService = Executors.newFixedThreadPool(5);	
		final List<Future<String>> resultList = new ArrayList<Future<String>>(); 
		try {
			for(String email : emailList){
				Future future = executorService.submit(new Callable(){
					public Object call() throws Exception {
						// 因為object to String 會包含[] ， 所以用replace把[]拿掉
						System.out.println("email="+email);
						sesUtil.send(email, sendEmail.getTitle(), sendEmail.getBody().replace("[", "").replace("]", ""),sendEmail.isHtml());
						return "已經送給管理員-"+email;
					}
				});
				resultList.add(future);
			}
			//監測Email執行續處理進度
			for (Future<String> fs : resultList){ 
				try{ 
					while(!fs.isDone());
					System.out.println(fs.get()); //打印各个线程（任务）执行的结果 
				}catch (InterruptedException | ExecutionException e) {
					logger.error("Email執行續 | 監控多執行續發生錯誤",e);
				}finally{
					executorService.shutdown();
				}
			}	
		} catch (Exception e) {
			final LogReport logReport = new LogReport();
    		logReport.setMessage(sendEmail.getLogMessage()+"-寄送Email失敗");
    		logReport.setFunction("sesUtil.send(to, title, message)");
    		logReport.setAttributes(sendEmail.getTo(), sendEmail.getTitle(), sendEmail.getBody());
        	logger.error(new Gson().toJson(logReport), e);
    		return false;
		}
		return true;
	}
		
}
