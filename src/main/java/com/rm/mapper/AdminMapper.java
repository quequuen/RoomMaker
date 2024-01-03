package com.rm.mapper;

import java.util.List;

import com.rm.model.FileVO;
import com.rm.model.MemberVO;
import com.rm.model.NoticeVO;
import com.rm.model.QuestionVO;
import com.rm.model.RoomMemberVO;
import com.rm.model.RoomVO;

public interface AdminMapper {
	
	//��� �� ���� ��������
	public List<RoomVO> getRoomList();
	
	//��� ȸ�� ���� ��������
	public List<MemberVO> getMemberList();
	
	//������ ȸ�� ���� ��������
	public MemberVO getDeleteMemberList(String id);
	
	//ȸ�� Ż��
	public void deleteMemberDo(String id);
	
	//���� ���� ��������
	public List<RoomVO> getRoomInfList(int roomcode);
	
	//���� �������� ��������
	public List<FileVO> getRoomFileList(int roomcode);
	
	//�� ���������� ��������
	public List<RoomMemberVO> getRoomMemberList(int roomcode);
	
	//�� ������ Ż��
	public void deleteRoomMemberList(int roomcode,String id);
	
	//Ż��� join_member_cnt ����
	public void DownMemberCnt(int roomcode);

	//���� ���� ����Ʈ ����
	public void deleteFileList(int roomcode,int filecode);
	
	//�� ����
	public void deleteAll(int roomcode);
	
	//���� ���� ���� ��������
	public List<NoticeVO> getNoticeList(int roomcode);
	
	//���� ���� ����
	public void deleteNoticeList(int roomcode,int noticecode);
	
	//���� ���� ����Ʈ ��������
	public List<QuestionVO> getQuestionList(int roomcode);
	
	//���� ���� ����Ʈ ����
	public void deleteQuestionList(int roomcode,int questioncode);
	
	//���� ���� ��� ����Ʈ ��������
	public List<QuestionVO> roomAnswerList(int questioncode);
	
	//���� ���� ��� ����
	public void deleteAnswerList(int roomcode, String answer);
}
