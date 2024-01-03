package com.rm.service;

import java.util.List;

import com.rm.model.AttachImageVO;

public interface AttachService {

	//이미지 데이터 반환
	public List<AttachImageVO> getAttachList(int roomcode);
	
}
