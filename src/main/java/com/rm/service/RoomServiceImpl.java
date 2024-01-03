package com.rm.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rm.mapper.RoomMapper;
import com.rm.model.PagingVO;
import com.rm.model.RoomMemberVO;
import com.rm.model.FileVO;
import com.rm.model.NoticeVO;
import com.rm.model.MemberVO;
import com.rm.model.QuestionVO;
import com.rm.model.RoomVO;

@Service
public class RoomServiceImpl implements RoomService{

	@Autowired
	private RoomMapper mapper;
	
	@Override
	public void makeRoom(RoomVO room) {
		mapper.makeRoom(room);
		
	}
	
	
	@Transactional
	@Override
	public void makerRoomDetail(RoomVO room) {
		mapper.makeRoomDetail(room);
		
		if(room.getImageList() == null || room.getImageList().size() <= 0) {
			return;
		}

		room.getImageList().forEach(attach ->{
			attach.setRoomcode(mapper.findRoomCode());
			mapper.imageEnroll(attach);
		});
		
	}

	@Override
	public int findRoomCode() {
		return mapper.findRoomCode();
	}


	//占쏙옙占쏙옙
	@Override
	public List<RoomVO> getRoomList() {
		
		return mapper.getRoomList();
		
	}


	//占쏙옙占쏙옙회
	@Override
	public RoomVO getRoomDetail(int roomcode) {
		
		return mapper.getRoomDetail(roomcode);
	}


	//占쏙옙체 占쏙옙 占쏙옙
	@Override
	public int countRoom(PagingVO vo) {
		
		return mapper.countRoom(vo);
	}


	//占쏙옙占쏙옙징 처占쏙옙 占쏙옙占쏙옙
	@Override
	public List<RoomVO> selectRoom(PagingVO vo) {
		System.out.println("占쏙옙占쏙옙징 : "+vo);
		return mapper.selectRoom(vo);
	}
	
	//file insert
	@Override
	public void uploadFile(FileVO file) {
		mapper.uploadFile(file);
	}
		
	//file code 媛��졇�삤湲�
	@Override
	public int selectFileCode(FileVO file) {
		return mapper.selectFileCode(file);
	}
		
	//filedetail insert
	@Override
	public void uploadFileDetail(FileVO file) {
		mapper.uploadFileDetail(file);
	}
	
	//fileList 媛��졇�삤湲�
	@Override
	public List<FileVO> getFileList(int roomcode, PagingVO vo){
		return mapper.getFileList(roomcode,vo);
	}
	
	//fileDetail 媛��졇�삤湲�
	@Override
	public FileVO getFileDetail(int roomcode, int filecode) {
		   System.out.println("serviceroomcode :"+roomcode);
		return mapper.getFileDetail(roomcode, filecode);
	}
	// 질문 목록 가져오기
	@Override
	public List<QuestionVO> question(int roomcode, PagingVO vo) {
		
		return mapper.question(roomcode,vo);
		
	}
	// insertQuestion 가져오기
	@Override
	public void insertQuestion(QuestionVO room) {
		
		mapper.insertQuestion(room);
		
	}
	
	@Override
	public void insertQuestionDetail(QuestionVO room) {
		
		//mapper.insertQuestionDetail(room);
		
	}
	

	@Override
	public int findQuestionCode() {
		
		return mapper.findQuestionCode();
		
		
	}
	
	@Override
	public QuestionVO getQuestionDetail(int roomcode, int questioncode) {
		
		System.out.println("" + roomcode);
		
		return mapper.getQuestionDetail(roomcode, questioncode);
	}
	
	//question 갯수 가져오기
	@Override
	public int questionCount(int roomcode) {
		
		return mapper.questionCount(roomcode);
	}
	
	//question 수정
	@Override
	public void updateQuestion(QuestionVO question, int questioncode) {
			
		mapper.updateQuestion(question, questioncode);
	}

	
	//fileList 갯수 가져오기
	@Override
	public int countFile(int roomcode) {
		return mapper.countFile(roomcode);
	}
	
	//deleteFile
	@Override
	public void deleteFile(int roomcode, int filecode) {
		mapper.deleteFile(roomcode, filecode);
	}
	
	//modifyFile
	@Override
	public void updateFile(FileVO file, int filecode) {
		mapper.updateFile(file, filecode);
	}
	
	//방 입장시 roommember 삽입
	@Override
	public void insertRoomMember(RoomMemberVO roommember) {
		mapper.insertRoomMember(roommember);;
	}


	//모든 roommember값 가져오기
	@Override
	public List<RoomMemberVO> selectRoomMember() {
		
		return mapper.selectRoomMember();
	}

	//join_member_cnt 증가
	@Override
	public void updateMemberCnt(int roomcode) {
		mapper.updateMemberCnt(roomcode);
	}


	//참여중인 방
	@Override
	public List<RoomVO> joinRoomList(String id, PagingVO vo) {
		return mapper.joinRoomList(id, vo);
	}

	//참여중인 방의 개수
	@Override
	public int countJoinRoom(String id) {
		
		return mapper.countJoinRoom(id);
	}


	//내가 만든 방
	@Override
	public List<RoomVO> makeRoomList(String id, PagingVO vo) {
		
		return mapper.makeRoomList(id, vo);
	}

	//내가 만든 방의 수
	@Override
	public int countMakeRoom(String id) {
		return mapper.countMakeRoom(id);
	}


	

	


	


	


	


	@Override
	public String selectName(String id) {
		return mapper.selectName(id);
	}


	@Override
	public List<MemberVO> roomMemberList(int roomcode) {

		return mapper.roomMemberList(roomcode);
	}


	@Override
	public void updateRoom(RoomVO vo) {
		mapper.updateRoom(vo);
	}
	
	/*공지 등록*/
	@Override
	public void noticeEnroll(NoticeVO notice) {
		mapper.noticeEnroll(notice);
	}
	
	
	
	@Override
	public int countNotice() {
		return mapper.countNotice();
	}
	/*공지 목록(페이징)*/
	@Override
	public List<NoticeVO> getNoticeList(int roomcode,PagingVO vo) {
		return mapper.getNoticeList(roomcode,vo);
	}

	/* 공지 디테일 */
    @Override
    public NoticeVO getNoticePage(int noticecode) {
         
        return mapper.getNoticePage(noticecode);
    }  
    
    /* 공지 수정 */
    @Override
    public void noticeModify(NoticeVO notice) {
    	mapper.noticeModify(notice);
    }
    
    //question 답변 등록
  	@Override
    public void enrollQAnswer(int roomcode, int questioncode, String answer, String answermember) {
  		mapper.enrollQAnswer(roomcode, questioncode, answer, answermember);
  	}
  	
  	//question 답변 가져오기
  	@Override
    public List<QuestionVO> getAnswerList(int roomcode,int questioncode){
  		return mapper.getAnswerList(roomcode, questioncode);
  	}


  	//답변 삭제하기
	@Override
	public void deleteAnswer(int roomcode, int questioncode, String answer) {
		
		mapper.deleteAnswer(roomcode, questioncode, answer);
	}
  	


	
}
