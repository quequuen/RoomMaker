package com.rm.model;

public class PagingVO {
	
	//����������, ����������, ��������, �Խñ� �� ����, �������� �� ����, ������������, SQL������ �� start, end
	private int nowPage, startPage, endPage, total, cntPerPage, lastPage,start,end;
	private int cntPage = 1;
	
	//�˻��� Ű����
	private String keyword;
	
	public PagingVO() {
		
	}
	
	public PagingVO(int total, int nowPage, int cntPerPage)
	{
		setNowPage(nowPage);
		setCntPerPage(cntPerPage);
		setTotal(total);
		calcLastPage(getTotal(), getCntPerPage());
		calcStartEndPage(getNowPage(), cntPage);
		calcStartEnd(getNowPage(), getCntPerPage());
	}
	
	//���� ������ ������ ���
	public void calcLastPage(int total, int cntPerPage)
	{
		setLastPage((int)Math.ceil((double)total / (double)cntPerPage));
		
	}
	
	//����, �� ������ ���
	public void calcStartEndPage(int nowPage, int cntPage) {
		System.out.println("���� ������ : "+nowPage+"/"+"�Խù� ���� : "+cntPage);
		setEndPage(((int)Math.ceil((double)nowPage / (double)cntPage)) * cntPage);
		if(getLastPage() < getEndPage()) {
			setEndPage(getLastPage());
		}
		setStartPage(getEndPage() - cntPage + 1);
		if(getStartPage() < 1)
		{
			setStartPage(1);
		}
	}
	
	//DB �������� ����� start, end�� ���
	public void calcStartEnd(int nowPage, int cntPerPage) {
		setEnd(nowPage * cntPerPage);
		setStart(getEnd() - cntPerPage + 1);
	}

	
	
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getCntPerPage() {
		return cntPerPage;
	}

	public void setCntPerPage(int cntPerPage) {
		this.cntPerPage = cntPerPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getCntPage() {
		return cntPage;
	}

	public void setCntPage(int cntPage) {
		this.cntPage = cntPage;
	}

	@Override
	public String toString() {
		return "PagingVO [nowPage=" + nowPage + ", startPage=" + startPage + ", endPage=" + endPage + ", total=" + total
				+ ", cntPerPage=" + cntPerPage + ", lastPage=" + lastPage + ", start=" + start + ", end=" + end
				+ ", cntPage=" + cntPage + ", keyword=" + keyword + "]";
	}

	
	
	

}
