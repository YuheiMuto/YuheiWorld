package com.movie.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * User情報
 * @author 
 *
 */
@Component
@Scope(value= "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String user_id;
	private String name;
	private Date birthday;
	private int pref_cd;
	private String password;
	
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public int getPref_cd() {
		return pref_cd;
	}
	public void setPref_cd(int pref_cd) {
		this.pref_cd = pref_cd;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
