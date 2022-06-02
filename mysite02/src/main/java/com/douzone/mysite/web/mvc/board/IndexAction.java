package com.douzone.mysite.web.mvc.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.repository.UserRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.WebUtil;

public class IndexAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		List<BoardVo> list = new BoardRepository().findAll();
		request.setAttribute("list",list);

		try {
			Integer.parseInt(request.getParameter("end"));
			
		}catch(NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/board?end=5");
			return;
		}
	
		// 접근제어/ 로그인하고 들어오도록 메인으로 보내버림.
		/////////////////////////////////////////////////////////////////////
		if(session == null) {
			WebUtil.redirect(request, response, request.getContextPath());
			return;
		}
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			
			WebUtil.forward(request, response, "board/index");
			return;
			}
		/////////////////////////////////////////////////////////////////////
		
		UserVo userVo = new UserRepository().findByNo(authUser.getNo());
		request.setAttribute("userVo", userVo);
		WebUtil.forward(request, response, "board/index");
	}



}