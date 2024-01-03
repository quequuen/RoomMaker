package com.rm.service;

import java.util.List;

import com.rm.model.MemberVO;

public interface MemberService {

	// 회원가입
	public void memberJoin(MemberVO member) throws Exception;

	public MemberVO memberLogin(MemberVO member) throws Exception;

	public int joinIdCheck(String id) throws Exception;

	// 마이페이지에서 정보 수정
	public void modifyMember(MemberVO member);

	// 회원탈퇴
	public void deleteMember(MemberVO member);
	
	//회원 탈퇴시 인원감소
	public List<Integer> getDeleteMemberRoomcode(String id);
	
	//아이디찾기(이메일)
	public String findIdByEmail(MemberVO member);
	
	//아이디찾기(전화번호)
	public String findIdByPhonenum(MemberVO member);
	
	//비밀번호 재설정(이메일)
	public void updatePwByEmail(MemberVO member);
	
	//비밀번호 재설정(비밀번호)
	public void updatePwByPhoneNum(MemberVO member);
	
	//재설정 전 이메일로 비밀번호 찾기
	public String findPwByEmail(MemberVO member);
		
	//재설정 전 전화번호로 비밀번호 찾기 
	public String findPwByPhoneNum(MemberVO member);
		

}
