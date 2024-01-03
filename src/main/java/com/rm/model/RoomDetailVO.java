package com.rm.model;

public class RoomDetailVO {
	
	private int roomcode;
	
	private String title;
	
	private String roompw;
	
	private int member_cnt;
	
	private String img;
	
	private String explanation;

	public int getRoomcode() {
		return roomcode;
	}

	public void setRoomcode(int roomcode) {
		this.roomcode = roomcode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRoompw() {
		return roompw;
	}

	public void setRoompw(String roompw) {
		this.roompw = roompw;
	}

	public int getMember_cnt() {
		return member_cnt;
	}

	public void setMember_cnt(int member_cnt) {
		this.member_cnt = member_cnt;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	@Override
	public String toString() {
		return "RoomDetailVO [roomcode=" + roomcode + ", title=" + title + ", roompw=" + roompw + ", member_cnt="
				+ member_cnt + ", img=" + img + ", explanation=" + explanation + "]";
	}
	
	
	

}
