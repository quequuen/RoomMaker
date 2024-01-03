package com.rm.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rm.mapper.AdminMapper;
import com.rm.mapper.RoomMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AdminMapperTest {
	private static final Logger log = org.slf4j.LoggerFactory.getLogger(RoomMapper.class);

	@Autowired
	AdminMapper mapper;
	
	@Test
	public void test()
	{
		System.out.println("방 질문 대답 리스트 : " + mapper.roomAnswerList(1));
	}

}
