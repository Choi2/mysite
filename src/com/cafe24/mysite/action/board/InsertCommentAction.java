package com.cafe24.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.WebUtil;
import com.cafe24.mysite.dao.BoardDao;
import com.cafe24.mysite.vo.CommentVo;
import com.cafe24.mysite.vo.UserVo;

public class InsertCommentAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserVo user = (UserVo) request.getSession().getAttribute("authUser");
		CommentVo comment = new CommentVo();
		String content = request.getParameter("content");
		long boardNo = Integer.parseInt(request.getParameter("no"));
		
		comment.setBoardNo(boardNo);
		comment.setUserNo(user.getNo());
		comment.setContent(content);
		BoardDao dao = new BoardDao();
		dao.insertComment(comment);
		System.out.println(comment);
		WebUtil.redirect(request, response, "/mysite/board?a=view&no="+ boardNo);
	}

}
