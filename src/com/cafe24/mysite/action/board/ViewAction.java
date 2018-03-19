package com.cafe24.mysite.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.WebUtil;
import com.cafe24.mysite.dao.BoardDao;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.CommentVo;

public class ViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long no = Integer.parseInt(request.getParameter("no"));
		
		BoardDao dao = new BoardDao();
		dao.updateReadCount(no);
		BoardVo vo = dao.get(no);
		if(vo.getBoardDelete() == 1) {
			WebUtil.forward(request, response, "/WEB-INF/views/errors/404.jsp");
			return ;
		}
		List<CommentVo> commentList = dao.getCommentList(no);
		vo.setContent(vo.getContent().replaceAll("\n", "<br/>"));
		request.setAttribute("vo", vo);
		request.setAttribute("commentList", commentList);
		//forwarding
		WebUtil.forward(request, response, "/WEB-INF/views/board/view.jsp");
	}

}
