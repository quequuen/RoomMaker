package com.rm.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rm.mapper.AttachMapper;
import com.rm.model.AttachImageVO;
import com.rm.model.PagingVO;
import com.rm.model.RoomVO;
import com.rm.service.AttachService;
import com.rm.service.RoomService;

import lombok.extern.log4j.Log4j;

/**
 * Handles requests for the application home page.
 */
@Log4j
@Controller
public class HomeController {
	

	private static final Logger log = LoggerFactory.getLogger(HomeController.class);
	
	
	@Autowired
   private AttachMapper attachMapper;
	
	@Autowired
	private RoomService service;
	
	@Autowired
	private AttachService imageService;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		log.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		//model.addAttribute("serverTime", formattedDate );
		
		model.addAttribute("roomList",service.getRoomList());
		
		System.out.println("깃허브 복구2222");
		
		System.out.println(service.getRoomList());
		
		System.out.println("메인 페이지 진입");
		
		return "home";
	}
	
	
	@GetMapping("/main")
	public String roomList(String keyword,PagingVO vo, Model model,String type, String id,
			@RequestParam(value="nowPage", required=false)String nowPage,
			@RequestParam(value="cntPerPage", required=false)String cntPerPage) {
		
		int total =0;
		List <RoomVO> roomList = new ArrayList<RoomVO>();
		
		if(type == null && id == null)
		{
			total = service.countRoom(vo);
			System.out.println("총합 : "+total);
			if(nowPage == null && cntPerPage == null) {
				nowPage="1";
				cntPerPage = "6";
			} else if(nowPage == null) {
				nowPage = "1";
			} else if(cntPerPage == null) {
				cntPerPage="6";
			}
			
			vo = new PagingVO(total,Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
			vo.setKeyword(keyword);
			
			
			System.out.println("페이징 : "+vo);
			model.addAttribute("paging",vo);
			
			roomList = service.selectRoom(vo);
			
			model.addAttribute("type","all");
			
			
		}
		else if(type.equals("joinroom"))
		{
			System.out.println("내가 참여한 방");
			
			total = service.countJoinRoom(id);
			System.out.println("join총합 : "+total);
			if(nowPage == null && cntPerPage == null) {
				nowPage="1";
				cntPerPage = "6";
			} else if(nowPage == null) {
				nowPage = "1";
			} else if(cntPerPage == null) {
				cntPerPage="6";
			}
			
			vo = new PagingVO(total,Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
			vo.setKeyword(keyword);
			
			
			System.out.println("페이징 : "+vo);
			model.addAttribute("paging",vo);
			
			roomList = service.joinRoomList(id,vo);
			
			model.addAttribute("type","joinroom");

			
		}
		
		else if(type.equals("makeroom"))
		{
			System.out.println("내가 만든 방");
			
			total = service.countMakeRoom(id);
			System.out.println("make총합 : "+total);
			if(nowPage == null && cntPerPage == null) {
				nowPage="1";
				cntPerPage = "6";
			} else if(nowPage == null) {
				nowPage = "1";
			} else if(cntPerPage == null) {
				cntPerPage="6";
			}
			
			vo = new PagingVO(total,Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
			vo.setKeyword(keyword);
			
			
			System.out.println("페이징 : "+vo);
			model.addAttribute("paging",vo);
			
			roomList = service.makeRoomList(id, vo);
			
			model.addAttribute("type","makeroom");
			
		}
		
		
		System.out.println("리스트(이미지 전) : "+roomList);
		
		roomList.forEach(room ->{
			int roomcode = room.getRoomcode();
			
			List<AttachImageVO> imageList = attachMapper.getAttachList(roomcode);
			
			room.setImageList(imageList);
		});
		
		model.addAttribute("roomList", roomList);
		
		System.out.println("리스트(이미지 후) : "+roomList);
		
		return "main";
	}
	
	@RequestMapping(value = "/23", method = RequestMethod.GET)
	public void home2355() {
		log.info("Welcome home! The client locale is {}.123123");
			
	}
	
	@GetMapping("/display")
	public ResponseEntity<byte[]> getImage(String fileName){
		File file = new File("c:\\upload\\" + fileName);
		
		ResponseEntity<byte[]> result = null;
		
		try {
			
			HttpHeaders header = new HttpHeaders();
			
			header.add("Content-type", Files.probeContentType(file.toPath()));
			
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/* 이미지 정보 반환 */
	@GetMapping(value="/getAttachList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<AttachImageVO>> getAttachList(int roomcode){
		
		log.info("getAttachList.........." + roomcode);
	
		//System.out.println("코드 : "+roomcode);
		
		return new ResponseEntity<List<AttachImageVO>>(attachMapper.getAttachList(roomcode), HttpStatus.OK);
		
	}
	
	

}
