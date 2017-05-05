package cn.com.chengziapp.cloudvoicechat.pushserver.beans;

import java.util.Map;

public class Vp2VpnsBean {
	private String appkey;
	private Map<String,Object> audience;
	private Map<String,Object> notification;
	//private Map<String,Object> message;
	private Map<String,Object> options;
	
	public String getAppkey() {
		return appkey;
	}
	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}
	public Map<String, Object> getAudience() {
		return audience;
	}
	public void setAudience(Map<String, Object> audience) {
		this.audience = audience;
	}
	public Map<String, Object> getNotification() {
		return notification;
	}
	public void setNotification(Map<String, Object> notification) {
		this.notification = notification;
	}
	/*public Map<String, Object> getMessage() {
		return message;
	}
	public void setMessage(Map<String, Object> message) {
		this.message = message;
	}*/
	public Map<String, Object> getOptions() {
		return options;
	}
	public void setOptions(Map<String, Object> options) {
		this.options = options;
	}
		
}
