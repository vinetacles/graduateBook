package com.egroup.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.egroup.amazon.cloudwatch.logback.entity.LogReport;
import com.google.gson.Gson;


public class Base64Util {
	 static final Logger LOGGER = LoggerFactory.getLogger(Base64Util.class);
	
	public String encode(String text) {
		final Base64.Encoder encoder = Base64.getEncoder();
		//編碼
		byte[] textByte = null;
		try {
			textByte = text.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			final LogReport logReport = new LogReport();
        	logReport.setMessage("encoder編譯錯誤 ");
        	LOGGER.error(new Gson().toJson(logReport), e);      
		}
		return encoder.encodeToString(textByte);
	}

	public String decode(String textByte) {
		final Base64.Decoder decoder = Base64.getDecoder();
		String text = null;
		if (textByte != null && !textByte.equals("")) {
			//解碼
			try {
				text = new String(decoder.decode(textByte), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				final LogReport logReport = new LogReport();
	        	logReport.setMessage("decoder編譯錯誤 ");
	        	LOGGER.error(new Gson().toJson(logReport), e);   
			}
		}
		return text;
	}
}
