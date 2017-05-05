package cn.com.chengziapp.cloudvoicechat.hostmachine.beans;

import java.util.Map;

public class HostMashineSendData extends HostMashineInfoBean{
	private String callingState;
	private String mediaType;
	private String dataPackage;
	private String dataType;
	private Map<String, Object> extra;
	
	public String getCallingState() {
		return callingState;
	}
	public void setCallingState(String callingState) {
		this.callingState = callingState;
	}
	public String getMediaType() {
		return mediaType;
	}
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	public String getDataPackage() {
		return dataPackage;
	}
	public void setDataPackage(String dataPackage) {
		this.dataPackage = dataPackage;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public Map<String, Object> getExtra() {
		return extra;
	}
	public void setExtra(Map<String, Object> extra) {
		this.extra = extra;
	}
		
}
