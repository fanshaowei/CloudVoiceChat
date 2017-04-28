package cn.com.chengziapp.cloudvoicechat.hostmachine.beans;

import java.util.Map;

public class HostSendVoice extends HostCalling{
	private String voicePackage;
	private Map<String,Object> extras;
	
	public String getVoicePackage() {
		return voicePackage;
	}
	public void setVoicePackage(String voicePackage) {
		this.voicePackage = voicePackage;
	}
	public Map<String, Object> getExtras() {
		return extras;
	}
	public void setExtras(Map<String, Object> extras) {
		this.extras = extras;
	}	
	
}
