package com.cafe24.web.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cafe24.mvc.util.WebUtil;
import com.cafe24.mysite.vo.UserVo;

@WebFilter({ "/board" })
public class LoginFilter implements Filter {

	private static final Set<String> authUserCheckFunctions = new HashSet<String>(Arrays.asList(
		     new String[] {"delete", "modifyform", "writeform", "insertcomment", "deletecomment"}
		));
	
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String actionName = (req.getParameter("a") == null) ? "" : req.getParameter("a");
		HttpSession session = req.getSession();
		UserVo user = (UserVo) session.getAttribute("authUser");
		
		if(authUserCheckFunctions.contains(actionName) && user == null) {
			WebUtil.forward(req, res, "/WEB-INF/views/user/loginform.jsp"); //로그인없이 url로 수정폼을 들어갈려는 것을 막을 수 있음.
			return;
		}

		chain.doFilter(request, response);
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
