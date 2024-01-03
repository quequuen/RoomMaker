package com.rm.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rm.mapper.MemberMapper;
import com.rm.model.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MemberMapperTests {

	@Autowired
	private MemberMapper membermapper;
	
//	@Test
//	public void memberJoin() throws Exception{
//		MemberVO member = new MemberVO();
//		
//		member.setId("spring_test");
//		member.setPw("spring_test");
//		member.setName("spring");
//		member.setEmail("spring_test");
//		member.setPhonenum("spring_test");
//		
//		membermapper.memberJoin(member);
//		
//		
//		
//	}
	
	@Test
	public void test()
	{
		System.out.println(membermapper.getDeleteMemberRoomcode("ok2241"));
	}
	
}
