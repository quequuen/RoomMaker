package com.rm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.rm.mapper.MemberMapper;
import com.rm.model.MemberVO;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	MemberMapper membermapper;
	
	@Override
	public void memberJoin(MemberVO member) throws Exception {
		
		membermapper.memberJoin(member);
		
	}
	
	@Override
	public MemberVO memberLogin(MemberVO member) throws Exception{
		return membermapper.memberLogin(member);
	}
	
	@Override
	public int joinIdCheck(String id) throws Exception{
		return membermapper.joinIdCheck(id);
	}
	
	@Override
	public void modifyMember(MemberVO member) {
		membermapper.modifyMember(member);
	}
	
	@Override
	public void deleteMember(MemberVO member) {
		membermapper.deleteMember(member);
	}

	@Override
	public List<Integer> getDeleteMemberRoomcode(String id) {
		
		return membermapper.getDeleteMemberRoomcode(id);
	}
	
	@Override
	public String findIdByEmail(MemberVO member) {
		return membermapper.findIdByEmail(member);
	}
	
	@Override
	public String findIdByPhonenum(MemberVO member) {
		return membermapper.findIdByPhonenum(member);
	}
	
	@Override
	public void updatePwByEmail(MemberVO member) {
		membermapper.updatePwByEmail(member);
	}
	
	@Override
	public void updatePwByPhoneNum(MemberVO member) {
		membermapper.updatePwByPhoneNum(member);
	}
	
	@Override
	public String findPwByEmail(MemberVO member) {
		return membermapper.findPwByEmail(member);
	}
	
	@Override
	public String findPwByPhoneNum(MemberVO member) {
		return membermapper.findIdByPhonenum(member);
	}
	
}
