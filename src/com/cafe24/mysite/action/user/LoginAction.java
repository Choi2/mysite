package com.cafe24.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.WebUtil;
import com.cafe24.mysite.dao.UserDao;
import com.cafe24.mysite.vo.UserVo;

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		
		UserVo authUser = new UserDao().get(email, password);
		if(authUser == null) {
			/* 인증실패 */
			//WebUtil.redirect(request, response, "/mysite/user?a=loginform&result=fail");
			request.setAttribute("result", "fail");
			request.setAttribute("email", email);
			WebUtil.forward(request, response, "/WEB-INF/views/user/loginform.jsp");			
			return ;
		} 
		
		/* 인증처리 */
		HttpSession session = request.getSession(true);
		session.setAttribute("authUser", authUser);
		
		WebUtil.redirect(request, response, "/mysite/main");	
	}

}
