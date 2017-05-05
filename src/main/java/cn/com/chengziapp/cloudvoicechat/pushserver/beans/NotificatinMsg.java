package cn.com.chengziapp.cloudvoicechat.pushserver.beans;

import java.util.ArrayList;
import java.util.Map;

public class NotificatinMsg {
	private String from;
	private String to;
	private int msg_count;
	private ArrayList<Map<String,Object>> msg_ids;
	private String voice_type;
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public int getMsg_count() {
		return msg_count;
	}
	public void setMsg_count(int msg_count) {
		this.msg_count = msg_count;
	}
	public ArrayList<Map<String, Object>> getMsg_ids() {
		return msg_ids;
	}
	public void setMsg_ids(ArrayList<Map<String, Object>> msg_ids) {
		this.msg_ids = msg_ids;
	}
	public String getVoice_type() {
		return voice_type;
	}
	public void setVoice_type(String voice_type) {
		this.voice_type = voice_type;
	}	
}
