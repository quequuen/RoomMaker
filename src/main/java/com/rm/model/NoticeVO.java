package com.rm.model;

import java.util.Date;

public class NoticeVO {
	private int roomcode;
	private int noticecode;
	private String title;
	private String content;
	private Date writedate;
	private String writemember;
	
	public int getRoomcode() {
		return roomcode;
	}
	public void setRoomcode(int roomcode) {
		this.roomcode = roomcode;
	}
	public int getNoticecode() {
		return noticecode;
	}
	public void setNoticecode(int noticecode) {
		this.noticecode = noticecode;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getWritedate() {
		return writedate;
	}
	public void setWritedate(Date writedate) {
		this.writedate = writedate;
	}
	public String getWritemember() {
		return writemember;
	}
	public void setWritemember(String writemember) {
		this.writemember = writemember;
	}
	
	@Override
	public String toString() {
		return "NoticeVO [roomcode=" + roomcode + ", noticecode=" + noticecode + ", title=" + title + ", content="
				+ content + ", writedate=" + writedate + ", writemember=" + writemember + "]";
	}
	
	
}
