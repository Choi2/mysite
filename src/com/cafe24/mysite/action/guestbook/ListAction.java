package com.cafe24.mysite.action.guestbook;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.WebUtil;
import com.cafe24.mysite.dao.GuestBookDao;
import com.cafe24.mysite.vo.GuestBookVo;

public class ListAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GuestBookDao dao = new GuestBookDao();
		List<GuestBookVo> list = dao.getList();
		List<GuestBookVo> newList = new ArrayList<GuestBookVo>();
		
		for(GuestBookVo vo : list) {
			vo.setContent(vo.getContent().replaceAll("\n", "<br/>"));
			newList.add(vo);
		}
		
		request.setAttribute("list", newList);
		
		//forwarding
		WebUtil.forward(request, response, "/WEB-INF/views/guestbook/list.jsp");
	}

}
