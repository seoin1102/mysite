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

public class UpdateFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		// 접근제어/ 로그인하고 들어오도록 메인으로 보내버림.
		/////////////////////////////////////////////////////////////////////
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

		UserVo userVo = new UserRepository().findByNo(authUser.getNo());
		request.setAttribute("userVo", userVo);
		System.out.println(userVo.getEmail());
		System.out.println(userVo.getName());
		WebUtil.forward(request, response, "user/updateform");
	}

}
