package com.cafe24.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.WebUtil;
import com.cafe24.mysite.dao.BoardDao;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.UserVo;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long no = Integer.parseInt(request.getParameter("no"));
		BoardDao dao = new BoardDao();
		BoardVo vo = dao.get(no);
		HttpSession session = request.getSession();
		UserVo user = (UserVo) session.getAttribute("authUser");
		
		if(user.getNo() != vo.getUserNo()) {
			WebUtil.forward(request, response, "/WEB-INF/views/user/loginform.jsp"); //로그인없이 url로 수정폼을 들어갈려는 것을 막을 수 있음.
			return;
		}
		
		dao.delete(no);
		WebUtil.redirect(request, response, "/mysite/board");
	}

}
