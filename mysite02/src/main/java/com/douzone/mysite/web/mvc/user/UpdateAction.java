package com.douzone.mysite.web.mvc.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.repository.UserRepository;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.WebUtil;

public class UpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		if(session == null) {
			WebUtil.redirect(request, response, request.getContextPath());
			return;
		}
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			WebUtil.redirect(request, response, request.getContextPath());
			return;
			}
		/////////////////////////////////////////////////////////////////////
	
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		UserVo vo = new UserVo();
		
		
		if(password==null) {
			return;
		}
		
		vo.setName(name);
		vo.setPassword(password);
		vo.setGender(gender);
		vo.setNo(authUser.getNo());

		new UserRepository().update(vo);
		authUser.setName(name);
		WebUtil.redirect(request, response, request.getContextPath() + "/user?a=updateform");

	}

}
