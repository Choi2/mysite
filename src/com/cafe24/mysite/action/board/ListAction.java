package com.cafe24.mysite.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.WebUtil;
import com.cafe24.mysite.dao.BoardDao;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.Pager;
import com.cafe24.mysite.vo.UserVo;
import com.cafe24.web.constant.ConstantVariables;

public class ListAction implements Action{


	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDao dao = new BoardDao();

		int currentPage = (request.getParameter("page") == null) 
						? 0 : Integer.parseInt(request.getParameter("page"));
		int currentDataSizePerPage = currentPage * ConstantVariables.PAGE_SIZE;
		int currentGroupPage = currentPage / ConstantVariables.PAGE_SIZE;
		System.out.println("currentGroupPage : " + currentGroupPage);
		int boardSize = dao.getCount(currentGroupPage * ConstantVariables.GROUP_SIZE);
		System.out.println("size = " + boardSize);
		Pager pager = new Pager(currentGroupPage, boardSize);
		List<BoardVo> list = dao.getList(currentDataSizePerPage);
		
		
		
		HttpSession session = request.getSession();
		
		UserVo vo = (UserVo) session.getAttribute("authUser");
		
		request.setAttribute("userNo", vo == null ? "" : vo.getNo());
		request.setAttribute("list", list);
		request.setAttribute("pager", pager);
		request.setAttribute("page", currentPage);
		System.out.println(pager);
		//forwarding
		WebUtil.forward(request, response, "/WEB-INF/views/board/list.jsp");
	}

}
