package com.rm.model;

public class AttachImageVO {
	
	/* ��� */
	private String uploadPath;
	
	/* uuid */
	private String uuid;
	
	/* ���� �̸� */
	private String fileName;
	
	/* ��ǰ id */
	private int roomcode;

	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getRoomcode() {
		return roomcode;
	}

	public void setRoomcode(int roomcode) {
		this.roomcode = roomcode;
	}

	
	
}
