package com.cafe24.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.WebUtil;
import com.cafe24.mysite.dao.UserDao;
import com.cafe24.mysite.vo.UserVo;

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("no");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		
		UserDao dao = new UserDao();
		UserVo vo = new UserVo();
		vo.setNo((long) Integer.parseInt(no));
		vo.setName(name);
		vo.setPassword(password);
		vo.setGender(gender);
		dao.update(vo);
		
		WebUtil.redirect(request, response, "/mysite/user?a=modifysuccess");
	}

}
