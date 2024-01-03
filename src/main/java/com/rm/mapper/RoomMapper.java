package com.rm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rm.model.AttachImageVO;
import com.rm.model.PagingVO;
import com.rm.model.RoomMemberVO;
import com.rm.model.FileVO;
import com.rm.model.NoticeVO;
import com.rm.model.MemberVO;
import com.rm.model.QuestionVO;
import com.rm.model.RoomVO;

public interface RoomMapper {
	
	//占쏙옙占쏙옙占�(room占쏙옙占싱븝옙 占쏙옙占쏙옙)
	public void makeRoom(RoomVO room);
	
	//占쏙옙占쏙옙占�_roomcode占쏙옙占쏙옙占쏙옙占쏙옙
	public int findRoomCode();
	
	//占쏙옙占쏙옙占�(roomdetail占쏙옙 占쏙옙占쏙옙)
	public void makeRoomDetail(RoomVO room);
	
	/* 占싱뱄옙占쏙옙 占쏙옙占� */
	public void imageEnroll(AttachImageVO vo);
	
	//占쏙옙 占쏙옙占�
	public List<RoomVO> getRoomList();
	
	//占쏙옙 占쏙옙회
	public RoomVO getRoomDetail(int roomcode);
	
	//占쏙옙 占쏙옙占쏙옙
	public int countRoom(PagingVO vo);
	
	//占쏙옙占쏙옙징 처占쏙옙 占쏙옙 占쏙옙회
	public List<RoomVO> selectRoom(PagingVO vo);
	
	//file count
	public int countFile(int roomcode);
	
	//file insert
	public void uploadFile(FileVO file);
	
	//file code 媛��졇�삤湲�
	public int selectFileCode(FileVO file);
	
	//filedetail insert
	public void uploadFileDetail(FileVO file);
	
	//fileList 媛��졇�삤湲�
	public List<FileVO> getFileList(int roomcode);
	
	//fileList 가져오기
	public List<FileVO> getFileList(int roomcode, PagingVO vo);
	
	//fileDetail 媛��졇�삤湲�
	public FileVO getFileDetail(int roomcode, int filecode);
	
	//file Delete
	public void deleteFile(int roomcode, int filecode);
	
	//file modify
	public void updateFile(FileVO file, int filecode);
	
	//roomMember에 삽입
	public void insertRoomMember(RoomMemberVO roomVO);
	
	//roommember값 모두 가져오기
	public List<RoomMemberVO> selectRoomMember();
	
	//member_cnt증가
	public void updateMemberCnt(int roomcode);
	
	//내가 참여중인 방
	public List<RoomVO> joinRoomList(String id,PagingVO vo);
	
	//참여중인 방의 개수
	public int countJoinRoom(String id);
	
	
	//내가 만든방
	public List<RoomVO> makeRoomList(String id, PagingVO vo);
	
	//내가 만든 방의 수 
	public int countMakeRoom(String id);
	
	//회원의 이름
	public String selectName(String id);
	

	/*공지 등록*/
	public void noticeEnroll(NoticeVO notice);

	//참여 회원 정보
	public List<MemberVO> roomMemberList(int roomcode);
	
	//방 정보 수정
	public void updateRoom(RoomVO vo);
	

	
	/*공지 리스트(페이징)*/
	public List<NoticeVO> getNoticeList(int roomcode, PagingVO vo);
	// 게시물 총 갯수
	public int countNotice();

	//questuion 삽입
	//public List<QuestionVO> question(int roomcode);
	
	//question 리스트 가져오기
	public List<QuestionVO> question(int roomcode, PagingVO vo);
	
	//글 작성
	public void insertQuestion(QuestionVO room);
	
	public int findQuestionCode();
	
	//public void insertQuestionDetail(QuestionVO room);
	
	//글 조회하기
	public QuestionVO getQuestionDetail(int roomcode, int questioncode);
	
	
	// question count
	public int questionCount(int roomcode);	
	
	//question 수정
	public void updateQuestion(QuestionVO question,int questioncode);
	
	/* 공지 디테일 */
    public NoticeVO getNoticePage(int noticecode);
    
    /*공지 수정*/
    public void noticeModify(NoticeVO notice);
    
    //question 답변 등록
    public void enrollQAnswer(int roomcode, int questioncode, String answer, String answermember);

    //question 답변 가져오기
    public List<QuestionVO> getAnswerList(int roomcode,int questioncode);
    
    //답변 삭제하기
    public void deleteAnswer(int roomcode, int questioncode, String answer);
}
