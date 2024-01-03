package com.rm.model;

public class MemberVO {

	private String id, pw, name, email, phonenum;
	private int gender, memberlevel;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public int getMemberlevel() {
		return memberlevel;
	}
	public void setMemberlevel(int memberlevel) {
		this.memberlevel = memberlevel;
	}
	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", pw=" + pw + ", name=" + name + ", email=" + email + ", phonenum=" + phonenum
				+ ", gender=" + gender + ", memberlevel=" + memberlevel + "]";
	}
	
	
}
