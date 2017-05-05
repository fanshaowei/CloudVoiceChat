package cn.com.chengziapp.cloudvoicechat.hostmachine.beans;

public class HostMashineGenericBean<E> {
	private E e;
	private long stateCode;
	
	public HostMashineGenericBean(E objE){
		this.e = objE;
	}

	public E getE() {
		return e;
	}

	public void setE(E e) {
		this.e = e;
	}

	public long getStateCode() {
		return stateCode;
	}

	public void setStateCode(long stateCode) {
		this.stateCode = stateCode;
	}
		
}
