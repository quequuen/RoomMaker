package com.rm.mapper;

import java.util.List;

import com.rm.model.AttachImageVO;

public interface AttachMapper {
	
	/* �̹��� ������ ��ȯ */
	public List<AttachImageVO> getAttachList(int bookId);

}
