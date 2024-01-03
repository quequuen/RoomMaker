package com.rm.model;

public class RoomMemberVO {

	private int roomcode;
	
	private String id;
	
	private int roomlevel;

	public int getRoomcode() {
		return roomcode;
	}

	public void setRoomcode(int roomcode) {
		this.roomcode = roomcode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getRoomlevel() {
		return roomlevel;
	}

	public void setRoomlevel(int roomlevel) {
		this.roomlevel = roomlevel;
	}

	@Override
	public String toString() {
		return "RoomMemberVO [roomcode=" + roomcode + ", id=" + id + ", roomlevel=" + roomlevel + "]";
	}
	
	
	
	
}
