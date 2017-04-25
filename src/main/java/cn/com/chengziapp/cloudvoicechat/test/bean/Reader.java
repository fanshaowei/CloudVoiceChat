package cn.com.chengziapp.cloudvoicechat.test.bean;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Reader {//implements UserDetails{
	
	@Id
	private String username;
	private String fullname;
	private String password;
		
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

}
