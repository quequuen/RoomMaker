package com.rm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rm.model.MemberVO;
import com.rm.service.AdminService;
import com.rm.service.MemberService;

@Controller
@RequestMapping(value="/mypage")
public class MypageController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private AdminService adminService;
	
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);

	@GetMapping(value="/main")
	public void mypageGet(HttpServletRequest request) {
		log.info("留덉씠 �럹�씠吏� 吏꾩엯");
		
	}
	
	@GetMapping(value="/modify")
	public String mypageModifyGet() {
		log.info("留덉씠�럹�씠吏� �닔�젙 吏꾩엯");
		return "/mypage/modify";
	}
	
	@PostMapping(value="/modify")
	public void mypageModifyPost(String id, String pw, String name, String email, String phonenum, HttpServletRequest request) {
		
		MemberVO member = new MemberVO();
		member.setId(id);
		member.setPw(pw);
		member.setName(name);
		member.setEmail(email);
		member.setPhonenum(phonenum);
		
		memberService.modifyMember(member);
		
		HttpSession session = request.getSession();
		session.setAttribute("member", member);
	}
	
	@GetMapping(value="/leave")
	public String leaveMemberPOST(String id, String pw, HttpServletRequest request) {
		log.info("leave 吏꾩엯");
		
		MemberVO member = new MemberVO();
		member.setId(id);
		member.setPw(pw);
		
		List<Integer> memberRoomcode = memberService.getDeleteMemberRoomcode(id);
		
		for(int i=0; i<memberRoomcode.size(); i++)
		{
			adminService.DownMemberCnt(memberRoomcode.get(i));
		}
		
		memberService.deleteMember(member);
		
		HttpSession session = request.getSession();
		log.info("session......");
		session.invalidate();
		log.info("session......");
		
		return "redirect:/member/login";
	}
	
	 //留덉씠�럹�씠吏� �씠�룞 �쟾 鍮꾨�踰덊샇 泥댄겕
    @GetMapping(value="/memberPwCheck")
    public void goMemberPwCheckBeforeMypage() {
    	log.info("留덉씠�럹�씠吏� 鍮꾨쾲 泥댄겕 吏꾩엯");
    }


}
