package com.cafe24.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.WebUtil;
import com.cafe24.mysite.dao.BoardDao;
import com.cafe24.mysite.vo.CommentVo;
import com.cafe24.mysite.vo.UserVo;

public class DeleteCommentAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long commentno = Integer.parseInt(request.getParameter("commentno"));
		long no = Integer.parseInt(request.getParameter("no"));
		BoardDao dao = new BoardDao();
		CommentVo comment = dao.getComment(commentno);
		
		HttpSession session = request.getSession();
		UserVo user = (UserVo) session.getAttribute("authUser");
		
		if(user.getNo() != comment.getUserNo()) {
			WebUtil.forward(request, response, "/WEB-INF/views/user/loginform.jsp"); //로그인없이 url로 수정폼을 들어갈려는 것을 막을 수 있음.
			return;
		}
		
		dao.deleteComment(commentno);
		WebUtil.redirect(request, response, "/mysite/board?a=view&no=" + no);
	}

}
