package com.rm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rm.mapper.AdminMapper;
import com.rm.model.FileVO;
import com.rm.model.MemberVO;
import com.rm.model.NoticeVO;
import com.rm.model.QuestionVO;
import com.rm.model.RoomMemberVO;
import com.rm.model.RoomVO;

import lombok.extern.log4j.Log4j;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	AdminMapper mapper;

	@Override
	public List<MemberVO> getMemberList() {
		return mapper.getMemberList();
	}

	@Override
	public List<RoomVO> getRoomList() {
		
		return mapper.getRoomList();
	}

	@Override
	public MemberVO getDeleteMemberList(String id) {
		
		return mapper.getDeleteMemberList(id);
	}

	@Override
	public void deleteMemberDo(String id) {
		mapper.deleteMemberDo(id);
	}

	@Override
	public List<FileVO> getRoomFileList(int roomcode) {
		return mapper.getRoomFileList(roomcode);
	}

	@Override
	public List<RoomMemberVO> getRoomMemberList(int roomcode) {
		
		return mapper.getRoomMemberList(roomcode);
	}

	@Override
	public List<RoomVO> getRoomInfList(int roomcode) {
		
		return mapper.getRoomInfList(roomcode);
	}

	@Override
	public void deleteRoomMemberList(int roomcode, String id) {
		mapper.deleteRoomMemberList(roomcode, id);
	}

	@Override
	public void DownMemberCnt(int roomcode) {
		mapper.DownMemberCnt(roomcode);
		
	}

	@Override
	public void deleteFileList(int roomcode, int filecode) {
		mapper.deleteFileList(roomcode, filecode);
	}

	@Override
	public void deleteAll(int roomcode) {
		mapper.deleteAll(roomcode);
	}

	@Override
	public List<NoticeVO> getNoticeList(int roomcode) {
		
		return mapper.getNoticeList(roomcode);
	}

	@Override
	public void deleteNoticeList(int roomcode, int noticecode) {
		mapper.deleteNoticeList(roomcode, noticecode);
	}

	@Override
	public List<QuestionVO> getQuestionList(int roomcode) {
		return mapper.getQuestionList(roomcode);
	}

	@Override
	public void deleteQuestionList(int roomcode, int questioncode) {
		mapper.deleteQuestionList(roomcode, questioncode);
	}

	@Override
	public List<QuestionVO> roomAnswerList(int questioncode) {
		return mapper.roomAnswerList(questioncode);
	}

	@Override
	public void deleteAnswerList(int roomcode, String answer) {
		mapper.deleteAnswerList(roomcode, answer);
	}

	
}
