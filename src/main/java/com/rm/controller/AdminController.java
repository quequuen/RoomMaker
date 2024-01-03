package com.rm.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rm.model.FileVO;
import com.rm.model.MemberVO;
import com.rm.model.NoticeVO;
import com.rm.model.QuestionVO;
import com.rm.model.RoomMemberVO;
import com.rm.model.RoomVO;
import com.rm.service.AdminService;

@Controller
@RequestMapping("/admin/*")
public class AdminController {

   private static final Logger log = LoggerFactory.getLogger(RoomController.class);

   @Autowired
   AdminService service;
   
	
   @RequestMapping(value = "/admin_main", method=RequestMethod.GET)
	public String adminMainGET(Model model)
	{
		
		log.info("관리자 메인 페이지 입장");
		
		List<MemberVO> adminMemberList = service.getMemberList();
		
		model.addAttribute("adminMemberList",adminMemberList);
		
		List<RoomVO> adminRoomList = service.getRoomList();
		
		model.addAttribute("adminRoomList",adminRoomList);
		
		
		return "admin/admin_main";
	}
   
   
   @GetMapping("/deleteMember")
   public void deleteMember(String id,Model model)
   {
	   MemberVO mvo = service.getDeleteMemberList(id);
	   System.out.println("넘어온 아이디 : " +id);
	   
	   model.addAttribute("deleteMember",mvo);
   }
   
   @GetMapping("/deleteMemberDo")
   public String deletMemberDo(String id)
   {
	   service.deleteMemberDo(id);
	   return "redirect:/admin/admin_main";
   }
   
   @GetMapping("/adminRoomDetail")
   public void adminRoomDetail(int roomcode,Model model)
   {	   
	   
	   List<RoomVO> roomList = service.getRoomInfList(roomcode);
	   model.addAttribute("adminInfList",roomList);
	   
	   List<FileVO> fileList = service.getRoomFileList(roomcode);
	   model.addAttribute("adminFileList",fileList);
	   
	   List<RoomMemberVO> roomMemberList = service.getRoomMemberList(roomcode);
	   model.addAttribute("adminRoomMemberList",roomMemberList);
	   
	   List<NoticeVO> noticeList = service.getNoticeList(roomcode);
	   model.addAttribute("adminNoticeList",noticeList);
	   
	   List<QuestionVO> questionList = service.getQuestionList(roomcode);
	   model.addAttribute("adminQuestionList",questionList);
	   
   }
   
   @GetMapping("/deleteRoomMemberList")
   public String deleteRoomMemberList(int roomcode,String id)
   {
	   System.out.println("관리자_참여 회원 삭제");
	   service.deleteRoomMemberList(roomcode, id);
	   service.DownMemberCnt(roomcode);
	   return "redirect:/admin/adminRoomDetail?roomcode="+roomcode;
   }
   
   @GetMapping("/deleteFileList")
   public String deleteFileList(int roomcode,int filecode)
   {
	   service.deleteFileList(roomcode, filecode);
	   return "redirect:/admin/adminRoomDetail?roomcode="+roomcode;
   }
   
   @GetMapping("/deleteAll")
   public String deleteAll(int roomcode)
   {
	    service.deleteAll(roomcode);
	    return "redirect:/admin/admin_main";
   }
   
   @GetMapping("/deleteNoticeList")
   public String deleteNoticeList(int roomcode, int noticecode)
   {
	   service.deleteNoticeList(roomcode, noticecode);
	   return "redirect:/admin/adminRoomDetail?roomcode="+roomcode;
   }
   
   @GetMapping("/deleteQuestionList")
   public String deleteQuestionList(int roomcode, int questioncode)
   {
	   service.deleteQuestionList(roomcode, questioncode);
	   return "redirect:/admin/adminRoomDetail?roomcode="+roomcode;
   }
   
   
   @GetMapping("/roomAnswerList")
   public void roomAnswerList(int questioncode,Model model)
   {
	   List<QuestionVO> answerList = service.roomAnswerList(questioncode);
	   model.addAttribute("answerList",answerList);   
   }
   
   @GetMapping("/deleteAnswerList")
   public String deleteAnswerList(int roomcode, String answer,int questioncode)
   {
	   service.deleteAnswerList(roomcode, answer);
		return "redirect:/admin/roomAnswerList?questioncode="+questioncode;
   }
}
