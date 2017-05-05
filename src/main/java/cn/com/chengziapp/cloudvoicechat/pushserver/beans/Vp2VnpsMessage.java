package cn.com.chengziapp.cloudvoicechat.pushserver.beans;

import java.util.Map;

public class Vp2VnpsMessage {
	private String title;
	private String type;
	private String content;
	private Map<String, Object> extras;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Map<String, Object> getExtras() {
		return extras;
	}
	public void setExtras(Map<String, Object> extras) {
		this.extras = extras;
	}
		
}
