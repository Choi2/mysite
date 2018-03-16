package com.cafe24.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.WebUtil;
import com.cafe24.mysite.dao.UserDao;
import com.cafe24.mysite.vo.UserVo;

public class JoinAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email= request.getParameter("email");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		
		UserDao dao = new UserDao();
		UserVo vo = new UserVo();
		vo.setName(name);
		vo.setPassword(password);
		vo.setEmail(email);
		vo.setGender(gender);
		dao.insert(vo);
		System.out.println(vo);
		// WebUtil.forward(request, response, "/WEB-INF/views/user/joinsuccess.jsp");
		WebUtil.redirect(request, response, "/mysite/user?a=joinsuccess");
	}

}
