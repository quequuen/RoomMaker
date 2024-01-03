package com.rm.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rm.mapper.RoomMapper;
import com.rm.model.RoomVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class RoomServiceTest {

	private static final Logger log = org.slf4j.LoggerFactory.getLogger(RoomMapper.class);

	
	@Autowired
	private RoomService service;
	
//	@Test
//	public void testMakeRoom()
//	{
//		
//		RoomVO vo = new RoomVO();
//		
//		vo.setTitle("테스트제목2");
//		vo.setId("테스트방장2");
//		//vo.setMain_img("1232");
//		vo.setMember_cnt(32);
//		vo.setExplanation("3332");
//		
//		vo.setRoomcode(42);
//		vo.setRoompw("테스트암호2");
//		vo.setType(12);
//		
//		service.makeRoom(vo);
//		service.makerRoomDetail(vo);
//	}
	
//	@Test
//	public void testGetList() {
//		service.getRoomList().forEach(room -> log.info(""+room));
//	}
	
	
	@Test
	public void testGET()
	{
		int roomcode = 44;
		
		log.info(""+service.getRoomDetail(roomcode));
	}
}
