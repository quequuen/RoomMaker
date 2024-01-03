package com.rm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.rm.model.MemberVO;


@Controller
public class ChatController {
	
	private static final Logger log = LoggerFactory.getLogger(RoomController.class);

	
//	@GetMapping("/room")
//	public void chat(Model model) {
//		
//		MemberVO member = (MemberVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		
//		
//		log.info("==================================");
//		log.info("@ChatController, GET Chat / Username : " + member.getName());
//		
//		model.addAttribute("userid", member.getName());
//	}
}
