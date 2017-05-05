package cn.com.chengziapp.cloudvoicechat.hostmachine.beans;

public class HostMashineRequest extends HostMashineInfoBean{	
	private String callingState;
		
	public String getCallingState() {
		return callingState;
	}
	public void setCallingState(String callingState) {
		if(callingState == null){
			this.callingState = "1";
		}else{
			this.callingState = callingState;
		}		
	}	
}
