package com.egroup.amazon.cloudwatch.logback.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LogReport {
	public String loginID;					// 登入編號
    public String timeStamp;				// 時間戳記
	public String message;               	// 錯誤訊息
    public String level;				 	// log等級
    public String threadName;				
    public String loggerName;            	// 產生log的檔案名稱
    public ThrowableProxy throwableProxy;	// catch丟出的錯誤訊息
    public StackTraceElement[] callerData;
    public Set<String> markers;
    public Map<String, String> mdcPropertyMap;  
    private String function;				// function名稱
    private List<Attribute> attributes = new ArrayList<>();     // 傳入參數
    private String deviceProduct;			// 裝置
    private String deviceManufacturer;
    private String os;						// 作業系統
    private String browserName;
    private String browserVersion;
    private String browserDescription;
    private String pageName;				// 所在頁面
    private String type;                    // log類型 (frontend:前端 ; backend:後端)
    private String placeName;				// 地點
    private String latitude;				// 緯度
    private String longitude;				// 經度
    private String ip;						// ip位置 
    // 程式控制
    private String json_attribute;     
    // 測試監測使用者行為使用(暫定)
    private Object subject;					// 主詞
    private String verb;					// 動詞
    private Object object;					// 受詞 
    private Object oldData;					// 舊資料
	public String getLoginID() {
		return loginID;
	}

	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public String getLoggerName() {
		return loggerName;
	}

	public void setLoggerName(String loggerName) {
		this.loggerName = loggerName;
	}

	public ThrowableProxy getThrowableProxy() {
		return throwableProxy;
	}

	public void setThrowableProxy(ThrowableProxy throwableProxy) {
		this.throwableProxy = throwableProxy;
	}

	public StackTraceElement[] getCallerData() {
		return callerData;
	}

	public void setCallerData(StackTraceElement[] callerData) {
		this.callerData = callerData;
	}

	public Set<String> getMarkers() {
		return markers;
	}

	public void setMarkers(Set<String> markers) {
		this.markers = markers;
	}

	public Map<String, String> getMdcPropertyMap() {
		return mdcPropertyMap;
	}

	public void setMdcPropertyMap(Map<String, String> mdcPropertyMap) {
		this.mdcPropertyMap = mdcPropertyMap;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public List<Attribute> getAttributes() {
		return attributes;
	}

	public String getDeviceProduct() {
		return deviceProduct;
	}

	public void setDeviceProduct(String deviceProduct) {
		this.deviceProduct = deviceProduct;
	}

	public String getDeviceManufacturer() {
		return deviceManufacturer;
	}

	public void setDeviceManufacturer(String deviceManufacturer) {
		this.deviceManufacturer = deviceManufacturer;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getBrowserName() {
		return browserName;
	}

	public void setBrowserName(String browserName) {
		this.browserName = browserName;
	}

	public String getBrowserVersion() {
		return browserVersion;
	}

	public void setBrowserVersion(String browserVersion) {
		this.browserVersion = browserVersion;
	}

	public String getBrowserDescription() {
		return browserDescription;
	}

	public void setBrowserDescription(String browserDescription) {
		this.browserDescription = browserDescription;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getJson_attribute() {
		return json_attribute;
	}

	public void setJson_attribute(String json_attribute) {
		this.json_attribute = json_attribute;
	}

	public void setAttributes(Object ...objects){
		final List<Attribute> attributes = new ArrayList<Attribute>();  
		Attribute attribute;
		for(int i=0; i<objects.length; i++) {
			attribute = new Attribute();
    		attribute.setValue(objects[i]);
    		attributes.add(attribute);
		}
		this.attributes = attributes;
		setParamsName();
	}	
	
	private void setParamsName(){
		final String functionName = getFunction();
		if (functionName.contains("(") && functionName.contains(")")) {
			final String answer = functionName.substring(functionName.indexOf("(")+1,functionName.indexOf(")"));
			final String [] names = answer.split(",");
			if (names.length == attributes.size()) {
				for(int i=0; i < attributes.size(); i++){
					attributes.get(i).setKey(names[i].trim());
				}
			}
		}
	}

	public Object getSubject() {
		return subject;
	}

	public void setSubject(Object subject) {
		this.subject = subject;
	}

	public String getVerb() {
		return verb;
	}

	public void setVerb(String verb) {
		this.verb = verb;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object ...object) {
		this.object = object;
	}

	public Object getOldData() {
		return oldData;
	}

	public void setOldData(Object oldData) {
		this.oldData = oldData;
	}
	
}
