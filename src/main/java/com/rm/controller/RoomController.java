package com.rm.controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.net.URLEncoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mysql.cj.Session;
import com.rm.mapper.AttachMapper;
import com.rm.model.AttachImageVO;
import com.rm.model.MemberVO;
import com.rm.model.NoticeVO;
import com.rm.model.RoomMemberVO;
import com.rm.model.PagingVO;
import com.rm.model.FileVO;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rm.mapper.AttachMapper;
import com.rm.model.AttachImageVO;
import com.rm.model.QuestionVO;
import com.rm.model.RoomVO;
import com.rm.service.AdminService;
import com.rm.service.MemberService;
import com.rm.service.RoomService;
import net.coobird.thumbnailator.Thumbnails;
	

@Controller
@RequestMapping("/room/*")
public class RoomController{
   private static final Logger log = LoggerFactory.getLogger(RoomController.class);
   
   @Autowired
   private AttachMapper attachMapper;
   
   @Autowired
   private RoomService roomService;
   
   @Autowired
   private AdminService adminService;
   
   @GetMapping("/makeRoom")
   public void makeRoomGET()
   {
      log.info("방생성(room)");
      
   }
   
   //방생성 메소드
   @PostMapping("/makeRoom")
   public String makerRoomPOST(RoomVO room)
   {
      log.info("RoomVO : "+room);
      roomService.makeRoom(room);
      room.setRoomcode(roomService.findRoomCode());
      roomService.makerRoomDetail(room);
	  
      RoomMemberVO roommembervo = new RoomMemberVO();
      
       roommembervo.setId(room.getId());
	   roommembervo.setRoomcode(room.getRoomcode());
	   roommembervo.setRoomlevel(1);
	   
	   roomService.insertRoomMember(roommembervo);
	   roomService.updateMemberCnt(room.getRoomcode());
      
      return "redirect:/main";
   }

	/* 첨부 파일 업로드 */
	@PostMapping(value="/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<AttachImageVO>> uploadAjaxActionPOST(MultipartFile[] uploadFile) {
		
		log.info("uploadAjaxActionPOST..........");
		
		/* 이미지 파일 체크 */
		for(MultipartFile multipartFile: uploadFile) {
			
			File checkfile = new File(multipartFile.getOriginalFilename());
			String type = null;
			
			try {
				type = Files.probeContentType(checkfile.toPath());
				log.info("MIME TYPE : " + type);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(!type.startsWith("image")) {
				
				List<AttachImageVO> list = null;
				return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
				
			}
			
		}// for
		
		String uploadFolder = "C:\\upload";
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date date = new Date();
		
		String str = sdf.format(date);
		
		String datePath = str.replace("-", File.separator);
		
		
		/* 폴더 생성 */
		File uploadPath = new File(uploadFolder, datePath);
		
		if(uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
		
		/* 이미저 정보 담는 객체 */
		List<AttachImageVO> list = new ArrayList();
		
		for(MultipartFile multipartFile : uploadFile)
		{
			
			AttachImageVO vo = new AttachImageVO();
			
			/* 파일 이름 */
			String uploadFileName = multipartFile.getOriginalFilename();			
			vo.setFileName(uploadFileName);
			vo.setUploadPath(datePath);
			
			/* uuid 적용 파일 이름 */
			String uuid = UUID.randomUUID().toString();
			vo.setUuid(uuid);
			
			uploadFileName = uuid + "_" + uploadFileName;
			
			/* 파일 위치, 파일 이름을 합친 File 객체 */
			File saveFile = new File(uploadPath, uploadFileName);
			
			/* 파일 저장 */
			try {
				multipartFile.transferTo(saveFile);
				
				File thumbnailFile = new File(uploadPath, "s_" + uploadFileName);	
				
				BufferedImage bo_image = ImageIO.read(saveFile);

					//비율 
					double ratio = 3;
					//넓이 높이
					int width = (int) (bo_image.getWidth() / ratio);
					int height = (int) (bo_image.getHeight() / ratio);					
				
				
				Thumbnails.of(saveFile)
		        .size(width, height)
		        .toFile(thumbnailFile);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
			list.add(vo);
		
		}
		
		ResponseEntity<List<AttachImageVO>> result = new ResponseEntity<List<AttachImageVO>>(list, HttpStatus.OK);

		return result;
	}
	
	
	@GetMapping("/display")
	public ResponseEntity<byte[]> getImage(String fileName){
		//File file = new File("c:\\upload\\" + fileName);
		
		File file = new File("C:\\upload\\" + fileName);
		
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
	
	/* 이미지 파일 삭제 */
	@PostMapping("/deleteFile")
	public ResponseEntity<String> deleteFile(String fileName){
		
		log.info("deleteFile........" + fileName);
		
		
		File file = null;
		
		try {
			//썸네일 파일 삭제
			file = new File("C:\\upload\\"+URLDecoder.decode(fileName,"UTF-8"));
			
			file.delete();
			
			//원본 파일 삭제
			String originFileName = file.getAbsolutePath().replace("s_", "");
			
			log.info("originFileName : " + originFileName);
			
			file = new File(originFileName);
			
			file.delete();
			
		} catch(Exception e)
		{
			e.printStackTrace();
			
			return new ResponseEntity<String>("fail",HttpStatus.NOT_IMPLEMENTED);
			
		}
		
		return new ResponseEntity<String>("success",HttpStatus.OK);
	}
		
		//방 상세 페이지
	   @GetMapping("/roomDetail")
	   public void roomDetailGET(int roomcode, Model model,HttpServletRequest request)
	   {
	      log.info("방상세");
	      	
	      HttpSession session = request.getSession();
	      session.setAttribute("roomDetail", roomService.getRoomDetail(roomcode));	      
	   }
	   
	   
	   
	   //멤버 체크
	   @ResponseBody
	   @GetMapping("/roomMemberCheck")
	   public String roomMemberCheck(String id,int roomcode)
	   {
		   System.out.println("방 입장2");
		   
		   RoomMemberVO roommembervo = new RoomMemberVO();
		   
		   System.out.println(roomcode+" / "+ id);
		  
		   //방장의 아이디
		   String masterId = roomService.getRoomDetail(roomcode).getId();
		   
		   //방안의 참여자 아이디
		   List<RoomMemberVO> roomMemberList = roomService.selectRoomMember();
		  
		   
		   String check = "";
		   		   
		   //방안의 참여자 인지 구분
		   boolean room_in_member = false;
		   
		   for(int i=0; i<roomMemberList.size(); i++)
		   {
			   if(id.equals(roomMemberList.get(i).getId()) && roomcode == roomMemberList.get(i).getRoomcode())
			   {
				   System.out.println("방안의 회원");
				   check="s";
				   return check;
			   }
			   else {
				   check="n";
			   }
		   }
		   
		   return check;
	   }
	   
	   
	   //방 입장
	   @GetMapping("/roomMemberIn")
	   public String roomMemberIn(String id, int roomcode, Model model)
	   {
		   System.out.println("방 입장");
	         
	         RoomMemberVO roommembervo = new RoomMemberVO();
	         
	        
	         //방장의 아이디
	         String masterId = roomService.getRoomDetail(roomcode).getId();
	         
	         //방안의 참여자 아이디
	         List<RoomMemberVO> roomMemberList = roomService.selectRoomMember();
	         
	                  
	         //방안의 참여자 인지 구분
	         boolean room_in_member = false;
	         
	         for(int i=0; i<roomMemberList.size(); i++)
	         {
	            if(id.equals(roomMemberList.get(i).getId()) && roomcode == roomMemberList.get(i).getRoomcode())
	            {
	               room_in_member = true;
	            }
	         }
	                  
	         //입장한 참여자가 방장일때 1 
	         if(masterId.equals(id) && !room_in_member)
	         {   
	            roommembervo.setId(id);
	            roommembervo.setRoomcode(roomcode);
	            roommembervo.setRoomlevel(1);
	            
	            roomService.insertRoomMember(roommembervo);
	            roomService.updateMemberCnt(roomcode);
	         }
	         //일반 참여자는 0
	         else if(!room_in_member){
	            
	            roommembervo.setId(id);
	            roommembervo.setRoomcode(roomcode);
	            roommembervo.setRoomlevel(0);
	            
	            roomService.insertRoomMember(roommembervo);
	            roomService.updateMemberCnt(roomcode);
	         }

		   
		   return "redirect:/room/roomDetail?roomcode="+roomcode;
	      	      
	   }
	   
	   
	   //방장명 가져오기
	   @RequestMapping(value = "/selectName")
	   @ResponseBody
	   public String selectRoomMemberName(@RequestParam("id") String id){
		   	System.out.println("ajax로 받은 아이디 : "+id);
	       return id;
	   }
	   
	   //방 암호 체크
	   @GetMapping("/roomPwCheck")
	   public void roomPwCheck(int roomcode,Model model)
	   {
		   log.info("방 암호 체크");
		   
		   model.addAttribute("roomDetail",roomService.getRoomDetail(roomcode));
		   model.addAttribute("roomMemberList",adminService.getRoomMemberList(roomcode));
	   }
	   
	   @GetMapping("/loadDynamicJSP")
	    public String loadDynamicJSP(@RequestParam String buttonValue,int roomcode, Model model ) {
	        String jspToInclude = determineJSP(buttonValue,roomcode,model);
	        
	        System.out.println("넘어온 코드 : "+roomcode);
	    
	        
           return jspToInclude;
       }

       // Your method to determine which JSP to include based on the buttonValue
       private String determineJSP(String buttonValue,int roomcode,Model model) {
          System.out.println("넘어온 buttonValue의 값 : "+buttonValue);
              String jspToInclude = "";
              
              
              // 버튼에 따라 로드할 JSP 결정
              if ("일정보기".equals(buttonValue)) {
                  jspToInclude = "/room/roomSchedule";
              } else if (("자료공유".equals(buttonValue))||("취소".equals(buttonValue))) {
                  jspToInclude = "/room/fileList";
                  
                  PagingVO paging = new PagingVO();
                  String nowPage = null;
                  String cntPerPage = null;
                  System.out.println("자료공유 roomcode="+roomcode);
                  
                  fileListAction(paging,model,roomcode,nowPage,cntPerPage);
                  
              } else if ("채팅하기".equals(buttonValue)) {
                  jspToInclude = "/room/roomChatting";
              } else if (("질문확인".equals(buttonValue))||("조회취소".equals(buttonValue))) {
            	  
            	log.info("질문 확인 목록 진입");
            	
            	PagingVO paging = new PagingVO();
            	String nowPage = null;
            	String cntPerPage = null;
            	System.out.println("자료공유 roomcode=" + roomcode);
            	questionListAction(paging,model,roomcode,nowPage,cntPerPage);
            	
                  jspToInclude = "/room/question";
              } else if ("공지보기".equals(buttonValue)) {
                  
                  model.addAttribute("list",roomService.getNoticeList(roomcode, null));
                                  
                  jspToInclude = "/room/noticeList";
                   
                  PagingVO paging = new PagingVO();
                   String nowPage = null;
                   String cntPerPage = null;
                   System.out.println("자료공유 roomcode="+roomcode);
                   
                   noticeListAction(paging,model,roomcode,nowPage,cntPerPage);
                   
               }else if ("공지등록".equals(buttonValue)) {
                   model.addAttribute("roomcode",roomcode);
                   jspToInclude = "/room/noticeEnroll";
                }
               else if ("자료등록".equals(buttonValue)) {
            	  model.addAttribute("roomcode",roomcode);
            	  jspToInclude = "/room/fileUpload";
              } else if ("방수정".equals(buttonValue)) {
            	  jspToInclude = "/room/roomUpdate";
              } else if("글 작성".equals(buttonValue)) {
            	  
            	  log.info("글 작성 진입");
            	              	  
            	  model.addAttribute("roomcode", roomcode);
            	              	  
            	  jspToInclude = "/room/questionWrite";

              } 

           return jspToInclude; 
       }
       
       
	   
	   @GetMapping("/roomAIQuestion")
	   private void questionAI()
	   {
		   log.info("AI 질문 페이지");
	   }
	   	   
	   @PostMapping("/fileListPaging")
	   public String fileListPaging(
	       @RequestParam(value = "nowPage", required = false) Integer nowPage,
	       @RequestParam(value = "cntPerPage", required = false) Integer cntPerPage,
	       int roomcode,
	       Model model) {
	       PagingVO paging = new PagingVO();

	       String nowPageStr = Integer.toString(nowPage);
	       String cntPerPageStr = Integer.toString(cntPerPage);
	       System.out.println("fileListPaging 이동함");
	       
	       fileListAction(paging, model, roomcode, nowPageStr, cntPerPageStr);

	       return "room/fileList";
	   }
	   
	   public void fileListAction(PagingVO vo, Model model, int roomcode,
			   @RequestParam(value="nowPage", required=false)String nowPage,
				@RequestParam(value="cntPerPage", required=false)String cntPerPage) {
		   
		   int total = roomService.countFile(roomcode);
		   System.out.println("fileListAction 이동함");
		   System.out.println("File total: "+total);
		   if(nowPage == null && cntPerPage == null) {
				nowPage="1";
				cntPerPage = "6";
			} else if(nowPage == null) {
				nowPage = "1";
			} else if(cntPerPage == null) {
				cntPerPage="6";
			}
		   
		   vo = new PagingVO(total,Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
			
			model.addAttribute("paging",vo);
			System.out.println(vo);
		   
		   
		   List<FileVO> fileList = new ArrayList<FileVO>();
   		   fileList = roomService.getFileList(roomcode,vo);
   		   System.out.println("fileList : "+fileList);
   			model.addAttribute("list",fileList);
	   }
	   
	 //등록 버튼 누르고 자료 공유방 파일 등록/VO 올리기
	   //자료 공유방 등록 POST
	   @PostMapping("/loadDynamicFileJSP")
	   public String loadDynamicFileJSP(@RequestParam int roomcode, MultipartFile[] uploadFile, String filetitle, String filemember, String content, String uploadPath, Model model ,HttpServletRequest request) throws Exception {
		 //파일 등록
		   log.info("파일 등록 중..................");
		   //오늘 날짜로 등록
		   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String Date = sdf.format(date);
			
			String uploadFileName = null;
			
			//폴더 생성
			File CreateDateFolder = new File(uploadPath, Date);
			
			//해당 폴더가 이미 있지 않다면 폴더 생성
			if(CreateDateFolder.exists() == false) {
				CreateDateFolder.mkdirs();
			}	
			
			uploadPath += "/"+Date;
	
			
			//기본 for
			for(int i = 0; i < uploadFile.length; i++) {
				log.info("-----------------------------------------------");
				log.info("파일 이름 : " + uploadFile[i].getOriginalFilename());
				log.info("파일 타입 : " + uploadFile[i].getContentType());
				log.info("파일 크기 : " + uploadFile[i].getSize());	
				
				//파일 이름 저장
				uploadFileName = uploadFile[i].getOriginalFilename();
				// uuid 적용 파일 이름 
				String uuid = UUID.randomUUID().toString();
				uploadFileName = uuid + "_" + uploadFileName;
				
				//파일 위치, 파일 이름을 합친 File 객체
				File saveFile = new File(uploadPath, uploadFileName);
				
				
				//파일 저장 
				try {
					uploadFile[i].transferTo(saveFile);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		   
		   //DB에 저장
		   log.info("file 등록 완료 VO 등록.................");
		   System.out.println("roomcode : "+roomcode);
		   System.out.println("filetitle : "+filetitle);
		   System.out.println("filemember : "+filemember);
		   System.out.println("content : "+content);
		   System.out.println("uploadPath : "+uploadPath);
		   System.out.println("FileName : "+uploadFileName);
		   
		   FileVO file = new FileVO();
		   file.setContent(content);
		   file.setFilemember(filemember);
		   file.setFileName(uploadFileName);
		   file.setFiletitle(filetitle);
		   file.setRoomcode(roomcode);
		   file.setUploadPath(uploadPath);
		   
		   roomService.uploadFile(file);
		   int filecode = roomService.selectFileCode(file);
		   file.setFilecode(filecode);
		   roomService.uploadFileDetail(file);
				
		   String result = determineJSP("자료공유", roomcode, model);
		   
		   return result;
		  
	   }
	   
	 //자료 공유방 등록 GET
	   @GetMapping("/fileDetail")
	   public String fileDetailGET(@RequestParam int roomcode, @RequestParam int filecode, Model model, HttpServletRequest request){
		   
		   log.info("fileDetail 진입");
		   FileVO fileDetail = roomService.getFileDetail(roomcode, filecode);
		   model.addAttribute("file",fileDetail);

		   return "/room/fileDetail";
		 
	   }
	   
	   //자료 다운로드
	   @PostMapping("/fileDownload")
	   public void fileDownloadPOST(@RequestParam int roomcode, @RequestParam int filecode, HttpServletRequest request, HttpServletResponse response) throws Exception{
		 
		   //response 사용하지 않음(file 이름 인코딩된 채 저장)
		   log.info("fileDownloading.............");
		   FileVO uploadedFile = roomService.getFileDetail(roomcode, filecode);
		   String filePath = uploadedFile.getUploadPath();
		   String fileName = uploadedFile.getFileName();
		   File file = new File(filePath,fileName);

		   //User-Agent : 어떤 운영체제로  어떤 브라우저를 서버( 홈페이지 )에 접근하는지 확인함
		   String header = request.getHeader("User-Agent");
		   String storeToFileName;
		   
		   if ((header.contains("MSIE")) || (header.contains("Trident")) || (header.contains("Edge"))) {
			    //인터넷 익스플로러 10이하 버전, 11버전, 엣지에서 인코딩 
			    storeToFileName = URLEncoder.encode(uploadedFile.getFileName(), "UTF-8");
			  } else {
			    //나머지 브라우저에서 인코딩
			    storeToFileName = new String(uploadedFile.getFileName().getBytes("UTF-8"), "iso-8859-1");
			  }
		   
		// 업로드할 파일 경로 설정
	        //String downloadPath = "C:\\Users\\PC\\Downloads";  // 실제 다운로드 폴더 경로로 변경해야 합니다.

	        String downloadPath = "/Users/hangayeon/Downloads";  // 실제 다운로드 폴더 경로로 변경해야 합니다
	        // 다운로드 폴더에 파일 복사
	        try {
	            Path targetPath = new File(downloadPath, storeToFileName).toPath();
	            Files.copy(file.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);
	        } catch (IOException e) {
	            e.printStackTrace();
	            // 예외 처리 로직 추가
	        }
	   }
	   
	   //파일 삭제
		@PostMapping("/deleteUploadedFile")
		public String deleteUploadedFile(int filecode, int roomcode, Model model){
			
			String buttonValue="자료공유";
			String result = null;
			log.info("deleteFile........" + filecode+"("+roomcode+")");
			FileVO fileDetail = roomService.getFileDetail(roomcode, filecode);
			File file = null;
			
			try {
				//원본파일삭제
				file = new File(fileDetail.getUploadPath()+"\\"+URLDecoder.decode(fileDetail.getFileName(), "UTF-8"));
				String FileName = file.getAbsolutePath();
				System.out.println("fileName:"+FileName);
				file = new File(FileName);
				if (file.exists()) {
				    file.delete();
				} else {
					System.out.println("해당 파일이 존재하지 않습니다.");
				}
				
				//DB 삭제
				roomService.deleteFile(roomcode, filecode);
				result = determineJSP(buttonValue,roomcode,model);
				
			} catch(Exception e) {
				e.printStackTrace();
				
			}
			
			return result;
		}
		
		@GetMapping("/goFileModify")
		public String goFileModify( @RequestParam int roomcode, int filecode, Model model) {
			FileVO file = roomService.getFileDetail(roomcode, filecode);
			model.addAttribute("file",file);
			
			return "/room/fileModify";
		}
		
		@PostMapping("/modifyUploadedFile")
		public String mofidyUloadedFile(@RequestParam int roomcode,@RequestParam int filecode,@RequestParam("filetitle") String filetitle,@RequestParam String content, Model model) {
			
			log.info("modifyFile.........");
			FileVO file = new FileVO();
			file.setFiletitle(filetitle);
			file.setContent(content);
			
			roomService.updateFile(file, filecode);
			
			String buttonValue= "자료공유";
			return determineJSP(buttonValue, roomcode, model);
			
		}
		
		@GetMapping("/roomMemberList")
		public void roomMemberList(int roomcode, Model model)
		{
			List<MemberVO> roomMemberList = roomService.roomMemberList(roomcode);
			
			model.addAttribute("roomMemberList",roomMemberList);
			model.addAttribute("roomcode",roomcode);
		}
		
		@GetMapping("/deleteRoomMemberList")
	    public String deleteRoomMemberList(int roomcode,String id)
	    {
		   System.out.println("참여 회원 삭제");
		   adminService.deleteRoomMemberList(roomcode, id);
		   adminService.DownMemberCnt(roomcode);
		   return "redirect:/room/roomMemberList?roomcode="+roomcode;
	    }
		
		
		@GetMapping("/deleteRoom")
		public String deleteRoom(int roomcode, Model model) {
			System.out.println(roomcode+"방삭제");
			adminService.deleteAll(roomcode);
			return "redirect:/main";
		}
		
		@GetMapping("/updateRoom")
		public String updateRoom(@ModelAttribute RoomVO vo,Model model) {
			
			System.out.println("방 내용 수정"+vo);
			roomService.updateRoom(vo);
			
			String buttonValue= "방수정";
			return "redirect:/room/roomDetail?roomcode="+vo.getRoomcode();
		}
	
		
		
		//공지
		/* 공지 등록 */
	      @PostMapping("/roomNoticeEnroll")
	      public String roomNoticeEnrollPOST(@RequestParam int roomcode,String title,String content,String writemember,Model model) {
	          
	         
	         System.out.println(roomcode+","+title+","+content+","+writemember);
	         
	         NoticeVO notice = new NoticeVO();
	         
	         notice.setRoomcode(roomcode);
	         notice.setTitle(title);
	         notice.setContent(content);
	         notice.setWritemember(writemember);
	         
	         
	         roomService.noticeEnroll(notice);
	            
	         	String buttonValue= "공지보기";
				return determineJSP(buttonValue, roomcode,model);
	      }
	      
	      
	      /* 공지 조회 */
	      @GetMapping("/noticeDetail")
	      public void noticeGetPageGET(int noticecode, Model model) {
	          
	          model.addAttribute("pageInfo", roomService.getNoticePage(noticecode));
	          
	      }
	      
	      /* 수정 페이지 이동 */
	      @GetMapping("/noticeModify")
	      public void noticeModifyGET(int noticecode, Model model) {
	          
	          model.addAttribute("pageInfo", roomService.getNoticePage(noticecode));
	          
	      }
	      
	      /* 페이지 수정 */
	      @PostMapping("/noticeModify")
			public String noticeModify(@RequestParam int roomcode,@RequestParam int noticecode,@RequestParam String title,@RequestParam String content, Model model) {
				
				log.info("modifynotice.........");
				NoticeVO notice = new NoticeVO();
				notice.setTitle(title);
				notice.setContent(content);
				notice.setRoomcode(roomcode);
				notice.setNoticecode(noticecode);
				System.out.println("수정 : "+ notice);
				roomService.noticeModify(notice);
				
				String buttonValue= "공지보기";
				return determineJSP(buttonValue, roomcode, model);
			}
	      
	      public void noticeListAction(PagingVO vo, Model model, int roomcode,
	              @RequestParam(value="nowPage", required=false)String nowPage,
	              @RequestParam(value="cntPerPage", required=false)String cntPerPage) {
	           
	           int total = roomService.countNotice();
	           System.out.println("fileListAction 이동함");
	           System.out.println("File total: "+total);
	           if(nowPage == null && cntPerPage == null) {
	              nowPage="1";
	              cntPerPage = "6";
	           } else if(nowPage == null) {
	              nowPage = "1";
	           } else if(cntPerPage == null) {
	              cntPerPage="6";
	           }
	           
	           vo = new PagingVO(total,Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
	           
	           model.addAttribute("paging",vo);
	           System.out.println(vo);
	           
	           
	           List<NoticeVO> noticeList = new ArrayList<NoticeVO>();
	              noticeList = roomService.getNoticeList(roomcode,vo);
	              System.out.println("noticeList : "+noticeList);
	              model.addAttribute("list", noticeList);
	        }
	      
	      @PostMapping("/noticeListPaging")
	      public String noticeListPaging(
	          @RequestParam(value = "nowPage", required = false) Integer nowPage,
	          @RequestParam(value = "cntPerPage", required = false) Integer cntPerPage,
	          int roomcode,
	          Model model) {
	          PagingVO paging = new PagingVO();

	          String nowPageStr = Integer.toString(nowPage);
	          String cntPerPageStr = Integer.toString(cntPerPage);
	          System.out.println("noticeListPaging 이동함");
	          
	          noticeListAction(paging, model, roomcode, nowPageStr, cntPerPageStr);

	          return "room/noticeList";
	      }
	   
	   	
	   
	
	
	   /* 질문하기 페이지 접속 */
	   @GetMapping("/question")
	   public void roomQuestionGET(int roomcode,Model model) {
	  
		   

	   }
	   
	   @PostMapping("/question")
	   public void roomQuestionPOST(QuestionVO roomcode) {
		   
		   log.info("QuestionVO : " + roomcode);
		   
	   }
	 
	   
	   // 게시글 작성 처리 
	   @PostMapping("/questionWrite")
	   public String roomQuestionWrtiePOST(@RequestParam int roomcode, String questiontitle, String questionmember, String content, Model model) throws Exception{// POST > () 괄호안에 roomDetail.jsp 안에 변수 값 4개를 넣어줌
		   
		   QuestionVO qvo = new QuestionVO();
		   
		   qvo.setRoomcode(roomcode);
		   qvo.setQuestiontitle(questiontitle);
		   qvo.setContent(content);
		   qvo.setQuestionmember(questionmember);
		   
		   roomService.insertQuestion(qvo);
		   int qcode = roomService.findQuestionCode();
		   
		   qvo.setQuestioncode(qcode);
		   
		   //roomService.insertQuestionDetail(qvo);
		   
		   
		   
		   
		   String buttonvalue = "질문확인"; // 스트링으로 담고 나서 넣어주거나 
		   
		   //String address = determineJSP(buttonValue,roomcode,model);
		   //return address;
		   
		  return determineJSP(buttonvalue,roomcode,model);
		    
	   }
	   
	   
	   /* 조회 하기 */
	   @GetMapping("/questionDetail")
	   public String roomQuestionDetailGET(@RequestParam int roomcode, @RequestParam int questioncode, Model model) {
		   
		   System.out.println("questionDetail : " + roomcode);
		   System.out.println("questionCode : " + questioncode);
		   
		   QuestionVO getQuestionDetail = roomService.getQuestionDetail(roomcode, questioncode);
		   List<QuestionVO> answerList = roomService.getAnswerList(roomcode, questioncode);
		   
		   model.addAttribute("questionDetail",getQuestionDetail);
		   model.addAttribute("answer",answerList);
		   
		   return "/room/questionDetail";
	   }
	   
	   @GetMapping("questionModify")
	   public String questionModify(@RequestParam int roomcode, int questioncode, Model model) {
		   
		   QuestionVO question = roomService.getQuestionDetail(roomcode, questioncode);
		   
		   model.addAttribute("question", question);
		   
		   return "/room/questionModify";
	   }
	   
	   @PostMapping("/modifyUploadedQuestion")
	   public String modifyUploadedQuestion(@RequestParam int roomcode, @RequestParam int questioncode, @RequestParam("questiontitle") String questiontitle,@RequestParam String content, Model model) {
		   
		   log.info("modifyQuestion........");
		   QuestionVO question = new QuestionVO();
		   question.setQuestiontitle(questiontitle);
		   question.setContent(content);

		   roomService.updateQuestion(question, questioncode);
		   
		   return roomQuestionDetailGET(roomcode,questioncode,model);
		   
	   }
	   	   
	   @PostMapping("/questionListPaging")
	   public String questionListPaging(
	       @RequestParam(value = "nowPage", required = false) Integer nowPage,
	       @RequestParam(value = "cntPerPage", required = false) Integer cntPerPage,
	       int roomcode,
	       Model model) {
	       PagingVO paging = new PagingVO();

	       String nowPageStr = Integer.toString(nowPage);
	       String cntPerPageStr = Integer.toString(cntPerPage);
	       System.out.println("questionListPaging 이동");
	       
	       questionListAction(paging, model, roomcode, nowPageStr, cntPerPageStr);

	       return "room/question";
	   }
	   
	   public void questionListAction(PagingVO vo, Model model,@RequestParam int roomcode,
			   @RequestParam(value="nowPage", required=false)String nowPage,
				@RequestParam(value="cntPerPage", required=false)String cntPerPage) {
		   
		   int total = roomService.questionCount(roomcode);
		   System.out.println("questionListAction 이동");
		   System.out.println("question total: "+total);
		   if(nowPage == null && cntPerPage == null) {
				nowPage="1";
				cntPerPage = "6";
			} else if(nowPage == null) {
				nowPage = "1";
			} else if(cntPerPage == null) {
				cntPerPage="6";
			}
		   
		   vo = new PagingVO(total,Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
			
			model.addAttribute("paging",vo);
			System.out.println(vo);
		   
		   
		   List<QuestionVO> list = new ArrayList<QuestionVO>();
   		   list = roomService.question(roomcode, vo);
   		   System.out.println("question : " + list);
   			model.addAttribute("list", list);
   			
   			
	   }
	   
	   @GetMapping("/roomNoticeDelete")
	   public String roomNoticeDelete(int noticecode,int roomcode,Model model)
	   {	   
		   adminService.deleteNoticeList(roomcode, noticecode);
		   String buttonValue= "공지보기";
	 	   return determineJSP(buttonValue, roomcode,model);
	   }
	   @GetMapping(value="/questionAnswerWindow")
	   public void questionAnswerWindowGet() {
		   log.info("question 답글 작성 윈도우 오픈");
	   }
	   
	   @PostMapping(value="/questionAnswerWindow")
	   public void questionAnswerWindowPOST(String answer, int questioncode, int roomcode, String answermember) {
		   log.info("question 답글 작성 중..........");
		   System.out.println(answer);
		   System.out.println(questioncode);
		   System.out.println(roomcode);
		   System.out.println(answermember);
		   
		   roomService.enrollQAnswer(roomcode, questioncode, answer, answermember);
		   
		   
	   }
	   
	   
	 @PostMapping(value="/deleteAnswer")
	 @ResponseBody
	 public void deleteAnswer(int questioncode, int roomcode, String answer)
	 {
		log.info("답변 삭제...");
		
		roomService.deleteAnswer(roomcode, questioncode, answer);
	 }
	   	   	  
}

