package cn.com.chengziapp.cloudvoicechat.hostmachine.enums;

public enum HostMachineStateCode {
	//主机与消息服务器的通信状态码
	HM_REQ_SVR(4000),
	HM_LEAVE_VOICE(4001),
	HM_LEAVE_IMG(4002),
	HM_CALLING_VOICE(4003),
	HM_CALLING_IMG(4004);	
	
	private long stateCode;
	private HostMachineStateCode(long stateCode){
		this.stateCode = stateCode;
	}
		
	public long getStateCode() {
		return stateCode;
	}
	public void setStateCode(long stateCode) {
		this.stateCode = stateCode;
	}	
	
}
