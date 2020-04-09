package com.how.cwh.vo;

public class Member {
	String email, password , name, phonenum , nickname;
	
	public Member() {
		
	}
	
	public Member(String email, String password, String name, String phonenum, String nickname) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.phonenum = phonenum;
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		return "Member [email=" + email + ", password=" + password + ", name=" + name + ", phonenum=" + phonenum
				+ ", nickname=" + nickname + "]";
	}

	
}
