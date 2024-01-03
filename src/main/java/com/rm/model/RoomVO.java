package com.rm.model;

import java.util.List;

public class RoomVO {
	//제목
	private String title;
	
	//암호
	private String roompw;
	
	//멤버수
	private int member_cnt;
	
	//참여 회원 수 
	private int join_member_cnt;
	
	//설명
	private String explanation;
	
	//룸코드
	private int roomcode;
	
	//방종류
	private int type;
	
	//방장 아이디
	private String id;
	
	/* 이미지 정보 */
	private List<AttachImageVO> imageList;
	

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

	
	public int getJoin_member_cnt() {
		return join_member_cnt;
	}

	public void setJoin_member_cnt(int join_member_cnt) {
		this.join_member_cnt = join_member_cnt;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public int getRoomcode() {
		return roomcode;
	}

	public void setRoomcode(int roomcode) {
		this.roomcode = roomcode;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<AttachImageVO> getImageList() {
		return imageList;
	}

	public void setImageList(List<AttachImageVO> imageList) {
		this.imageList = imageList;
	}

	@Override
	public String toString() {
		return "RoomVO [title=" + title + ", roompw=" + roompw + ", member_cnt=" + member_cnt + ", join_member_cnt="
				+ join_member_cnt + ", explanation=" + explanation + ", roomcode=" + roomcode + ", type=" + type
				+ ", id=" + id + ", imageList=" + imageList + "]";
	}

	
}
