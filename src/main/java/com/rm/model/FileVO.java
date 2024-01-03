package com.rm.model;

import java.util.Date;

public class FileVO {
	private int roomcode, filecode;
	private String filetitle, filemember, content, fileName, uploadPath;
	private Date fileuploaddate;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getUploadPath() {
		return uploadPath;
	}
	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	public int getRoomcode() {
		return roomcode;
	}
	public void setRoomcode(int roomcode) {
		this.roomcode = roomcode;
	}
	public int getFilecode() {
		return filecode;
	}
	public void setFilecode(int filecode) {
		this.filecode = filecode;
	}
	public String getFiletitle() {
		return filetitle;
	}
	public void setFiletitle(String filetitle) {
		this.filetitle = filetitle;
	}
	public String getFilemember() {
		return filemember;
	}
	public void setFilemember(String filemember) {
		this.filemember = filemember;
	}
	public Date getFileuploaddate() {
		return fileuploaddate;
	}
	public void setFileuploaddate(Date fileuploaddate) {
		this.fileuploaddate = fileuploaddate;
	}
	@Override
	public String toString() {
		return "FileVO [roomcode=" + roomcode + ", filecode=" + filecode + ", filetitle=" + filetitle + ", filemember="
				+ filemember + ", content=" + content + ", fileName=" + fileName + ", uploadPath=" + uploadPath
				+ ", fileuploaddate=" + fileuploaddate + "]";
	}
	
	
	

}
