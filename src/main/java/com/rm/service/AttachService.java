package com.rm.service;

import java.util.List;

import com.rm.model.AttachImageVO;

public interface AttachService {

	//�̹��� ������ ��ȯ
	public List<AttachImageVO> getAttachList(int roomcode);
	
}
