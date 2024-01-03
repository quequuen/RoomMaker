package com.rm.mapper;

import java.util.List;

import com.rm.model.MemberVO;

public interface MemberMapper {

	//�쉶�썝媛��엯
	public void memberJoin(MemberVO member);
	
	//濡쒓렇�씤
	public MemberVO memberLogin(MemberVO member);
	
	//�븘�씠�뵒 以묐났 寃��궗
	public int joinIdCheck(String id);
	
	//留덉씠�럹�씠吏��뿉�꽌 �젙蹂� �닔�젙
	public void modifyMember(MemberVO member);
	
	//�쉶�썝�깉�눜
	public void deleteMember(MemberVO member);
	
	//회원 탈퇴
	public List<Integer> getDeleteMemberRoomcode(String id);
	
	//아이디 찾기(이메일)
	public String findIdByEmail(MemberVO member);
	
	//아이디 찾기(전화번호)
	public String findIdByPhonenum(MemberVO member);
	
	//재설정 전 이메일로 비밀번호 찾기
	public String findPwByEmail(MemberVO member);
	
	//재설정 전 전화번호로 비밀번호 찾기 
	public String findPwByPhoneNum(MemberVO member);
	
	//비밀번호 재설정(이메일)
	public void updatePwByEmail(MemberVO member);
	
	//비밀번호 재설정(전화번호)
	public void updatePwByPhoneNum(MemberVO member);
}
