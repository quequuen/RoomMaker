package com.rm.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rm.mapper.AttachMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AttachMapperTests {

	@Autowired
	private AttachMapper attachMapper;
	
	@Test
	public void getAttachListTests() {
		
		int roomcode = 50;
		
		System.out.println("이미지 정보 : "+attachMapper.getAttachList(roomcode).get(0).getFileName());
	}
	
}
