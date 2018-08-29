package com.egroup.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.egroup.amazon.cloudwatch.logback.entity.LogReport;
import com.google.gson.Gson;

public class HtmlUtil {
	private static Logger logger = LoggerFactory.getLogger(HtmlUtil.class);
	
	/**
	 * 讀取Html檔案內容，轉換成String格式
	 * @param filePath
	 * @return 
	 * @throws FileNotFoundException
	 */
	public String htmlToString (String filePath) throws FileNotFoundException {
		String data="";
		String result="";
		BufferedReader bufferedReader = null;
		FileInputStream fileInputStream = null;
		fileInputStream = new FileInputStream(filePath);
		if (fileInputStream != null) {
			try {
				bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, "UTF-8"));
				while ((data = bufferedReader.readLine()) != null) {
					result += data;
				}
			} catch (IOException e) {
				e.printStackTrace();
				final LogReport logReport = new LogReport();
				logReport.setMessage("IOException");
				logger.error(new Gson().toJson(logReport), e);
			} finally {
				try {
					bufferedReader.close();
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
					final LogReport logReport = new LogReport();
					logReport.setMessage("IOException");
					logger.error(new Gson().toJson(logReport), e);
				}
			}
		}
		return result;
	}
}
