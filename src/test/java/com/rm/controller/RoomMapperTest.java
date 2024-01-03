package com.rm.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rm.mapper.RoomMapper;
import com.rm.model.RoomMemberVO;
import com.rm.model.AttachImageVO;
import com.rm.model.QuestionVO;
import com.rm.model.RoomVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class RoomMapperTest {
	private static final Logger log = org.slf4j.LoggerFactory.getLogger(RoomMapper.class);

	@Autowired
	private RoomMapper mapper;
	
//	@Test
//	public void testRoom()
//	{
//		RoomVO vo = new RoomVO();
//		
////		vo.setTitle("�׽�Ʈ����");
////		vo.setId("�׽�Ʈ����");
////		vo.setMain_img("123");
////		vo.setMember_cnt(3);
////		vo.setExplanation("333");
////		
////		vo.setRoomcode(4);
////		vo.setRoompw("�׽�Ʈ��ȣ");
////		vo.setType(1);
////		
////		mapper.makeRoom(vo);
//		//mapper.makeRoomDetail(vo);
//		
//		System.out.println(mapper.findRoomCode());
//	}
	
//	@Test
//	public void testGetList()
//	{
//		
//		List list = mapper.getRoomList();
//		
//		for(int i=0; i<list.size(); i++)
//		{
//			log.info(""+list.get(i));
//		}
//	}
	
//	@Test
//	public void testGetPage() {
//		
//		int roomcode =44;
//		
//		log.info(""+mapper.getRoomDetail(roomcode));
//	}
	
//	@Test
//	public void testPaging()
//	{
//		System.out.println("�Խù� ���� : "+mapper.countRoom());
//		//System.out.println("�Խù� ����¡ ó�� : "+mapper.selectRoom(null));
//	}
	
//	@Test
//	public void testMemberIn()
//	{
//		RoomMemberVO rmvo = new RoomMemberVO();
//		
//		rmvo.setId("oo");
//		rmvo.setRoomcode(60);
//		rmvo.setRoomlevel(0);
//		
//		mapper.insertRoomMember(rmvo);
//	}
	
//	@Test
//	public void testSelectMember()
//	{
//		System.out.println(mapper.selectRoomMember());
//	}
	
//	@Test
//	public void testMemberCnt()
//	{
//		
//		mapper.updateMemberCnt(60);
//	}
	
//	@Test
//	public void testJoinRoom()
//	{
//		System.out.println(mapper.countJoinRoom("ok2241"));
//	}
	
//	@Test
//	public void testSelectName()
//	{
//		RoomVO vo = new RoomVO();
//		
//		vo.setRoomcode(73);
//		vo.setTitle("�ڹ� ��ȭ��");
//		vo.setRoompw("123");
//		vo.setMember_cnt(10);
//		vo.setJoin_member_cnt(1);
//		vo.setExplanation("�ڹ� ��ȭ���Դϴ�.");
//		
//		mapper.updateRoom(vo);
//		System.out.println("�Խù� ���� : "+mapper.countRoom());
//		//System.out.println("�Խù� ����¡ ó�� : "+mapper.selectRoom(null));
//	}
//	
//	@Test
//	public void testQuestion() {
//		
//		QuestionVO vo = new QuestionVO();
//		
//		vo.setRoomcode(14);
//		vo.setQuestioncode(15);
//		vo.setQuestiontitle("quest_test");
//		vo.setQuestiondate(null);
//		vo.setQuestionmember("quest_test");
//		vo.setContent("quest_test");
//		vo.setAnswer("quest_test");
//		vo.setAnswerdate("quest_test");
//		vo.setAnswermember("quest_test");
//		
//		mapper.question(vo);
//	
//	}
	
//	@Test
//	public void test01() {
//		
//		QuestionVO vo = new QuestionVO();
//		
//		vo.setRoomcode(1);
//		vo.setQuestiontitle("afafa");
//		vo.setQuestiondate(null);
//		vo.setQuestionmember("aa");
//		
//		//mapper.insertQuestion(vo);
//		
//		int num = mapper.findQuestionCode();
//		vo.setQuestioncode(num);
//		vo.setContent("gaga");
//		vo.setAnswer("afaf");
//		vo.setAnswermember("admin");
//		
//		mapper.insertQuestionDetail(vo);
//		
//		
//		
//	}
	
//	@Test
//	public void test02() {
//		List<QuestionVO> list = mapper.question(1);
//	
//		System.out.println(list);
//		
//	
//	}

//	@Test
//	public void testIQ() {
//		List<QuestionVO> list = mapper.question(1);
//		
//		QuestionVO vo = new QuestionVO();
//		vo.setRoomcode(1);
//		vo.setQuestiontitle("IQ test");
//		vo.setQuestionmember("IQ test");
//		
//		System.out.println(list);
//		
//	}
	
	
	
}
