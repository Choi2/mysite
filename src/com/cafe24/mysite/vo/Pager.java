package com.cafe24.mysite.vo;

import com.cafe24.web.constant.ConstantVariables;

public class Pager {
	
	private int startGroupPage;
	private int startPage;
	private int endPage;
	
	
	public Pager(int currentGroupPage, int boardSize) {
		this.startGroupPage = currentGroupPage;
		this.startPage = (currentGroupPage * ConstantVariables.PAGE_SIZE) + 1;
/*		this.endPage = (boardSize / ((currentGroupPage + 1) * ConstantVariables.PAGE_SIZE)) > startPage 
				?  (boardSize / ((currentGroupPage + 1) * ConstantVariables.PAGE_SIZE)) : startPage;*/
		
		//  ((boardSize / ConstantVariables.PAGE_SIZE) == ConstantVariables.PAGE_SIZE) ?
		// (ConstantVariables.PAGE_SIZE - 1) : (boardSize / ConstantVariables.PAGE_SIZE);
		this.endPage = this.startPage + (boardSize / ConstantVariables.PAGE_SIZE);
	}
	
	public int getStartGroupPage() {
		return startGroupPage;
	}
	public void setStartGroupPage(int startGroupPage) {
		this.startGroupPage = startGroupPage;
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
	
	@Override
	public String toString() {
		return "pager [startGroupPage=" + startGroupPage  + ", startPage=" + startPage
				+ ", endPage=" + endPage + "]";
	}
}
