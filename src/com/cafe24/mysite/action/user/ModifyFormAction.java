package com.cafe24.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.WebUtil;
import com.cafe24.mysite.dao.UserDao;
import com.cafe24.mysite.vo.UserVo;

public class ModifyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserVo authUser = (UserVo) request.getSession().getAttribute("authUser");
		UserDao dao = new UserDao();
		authUser = dao.get(authUser.getNo());
		System.out.println(authUser);
		request.setAttribute("authUser", authUser);
		WebUtil.forward(request, response, "/WEB-INF/views/user/modifyform.jsp");
	}

}
