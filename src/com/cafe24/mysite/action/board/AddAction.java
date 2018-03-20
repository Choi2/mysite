package com.cafe24.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.WebUtil;
import com.cafe24.mysite.dao.BoardDao;
import com.cafe24.mysite.vo.BoardVo;

public class AddAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userNo = request.getParameter("userno");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int groupNo = Integer.parseInt(request.getParameter("groupno"));
		int orderNo = Integer.parseInt(request.getParameter("otherno"));
		int parentNo = Integer.parseInt(request.getParameter("parentno"));
		int depth = Integer.parseInt(request.getParameter("depth")) ;

		BoardDao dao = new BoardDao();
		BoardVo vo = new BoardVo();
		vo.setUserNo(Integer.parseInt(userNo));
		vo.setTitle(title);
		vo.setContent(content);	
		vo.setGroupNo(groupNo);
		vo.setOrderNo(orderNo);
		vo.setParentNo(parentNo);
		vo.setDepth(depth);
		dao.arrangeList(vo);
		dao.insert(vo);

		

		WebUtil.redirect(request, response, "/mysite/board");
	}

}
