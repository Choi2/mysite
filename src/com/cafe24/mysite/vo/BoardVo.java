package com.cafe24.mysite.vo;

import java.util.Date;

public class BoardVo {
	private long no;
	private String title;
	private String content;
	private int readCount;
	private Date regDate;
	private long userNo;
	private String name;
	private long groupNo;
	private long orderNo;
	private long parentNo;
	private long depth;
	private int boardDelete;
	
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public long getUserNo() {
		return userNo;
	}
	public void setUserNo(long userNo) {
		this.userNo = userNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getGroupNo() {
		return groupNo;
	}
	public void setGroupNo(long groupNo) {
		this.groupNo = groupNo;
	}
	public long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}
	public long getDepth() {
		return depth;
	}
	public void setDepth(long depth) {
		this.depth = depth;
	}
	
	public int getBoardDelete() {
		return boardDelete;
	}
	public void setBoardDelete(int boardDelete) {
		this.boardDelete = boardDelete;
	}
	public long getParentNo() {
		return parentNo;
	}
	public void setParentNo(long parentNo) {
		this.parentNo = parentNo;
	}
	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", title=" + title + ", content=" + content + ", readCount=" + readCount
				+ ", regDate=" + regDate + ", userNo=" + userNo + ", name=" + name + ", groupNo=" + groupNo
				+ ", orderNo=" + orderNo + ", parentNo=" + parentNo + ", depth=" + depth + ", boardDelete="
				+ boardDelete + "]";
	}


	
}
