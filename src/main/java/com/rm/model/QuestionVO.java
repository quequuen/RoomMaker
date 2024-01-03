package com.rm.model;

import java.util.Date;

public class QuestionVO {
	
	private int roomcode;
	private int questioncode;
	private String questiontitle;
	private Date questiondate;
	private String questionmember;
	private String content;
	private String answer;
	private Date answerdate;
	private String answermember;
	
	public int getRoomcode() {
		return roomcode;
	}
	public void setRoomcode(int roomcode) {
		this.roomcode = roomcode;
	}
	public int getQuestioncode() {
		return questioncode;
	}
	public void setQuestioncode(int questioncode) {
		this.questioncode = questioncode;
	}
	public String getQuestiontitle() {
		return questiontitle;
	}
	public void setQuestiontitle(String questiontitle) {
		this.questiontitle = questiontitle;
	}
	public Date getQuestiondate() {
		return questiondate;
	}
	public void setQuestiondate(Date questiondate) {
		this.questiondate = questiondate;
	}
	public String getQuestionmember() {
		return questionmember;
	}
	public void setQuestionmember(String questionmember) {
		this.questionmember = questionmember;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Date getAnswerdate() {
		return answerdate;
	}
	public void setAnswerdate(Date answerdate) {
		this.answerdate = answerdate;
	}
	public String getAnswermember() {
		return answermember;
	}
	public void setAnswermember(String answermember) {
		this.answermember = answermember;
	}
	@Override
	public String toString() {
		return "QuestionVO [roomcode=" + roomcode + ", questioncode=" + questioncode + ", questiontitle="
				+ questiontitle + ", questiondate=" + questiondate + ", questionmember=" + questionmember + ", content="
				+ content + ", answer=" + answer + ", answerdate=" + answerdate + ", answermember=" + answermember
				+ "]";
	}
	
	
	
	

}
