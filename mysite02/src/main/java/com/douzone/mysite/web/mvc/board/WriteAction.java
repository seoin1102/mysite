package com.douzone.mysite.web.mvc.board;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.WebUtil;

public class WriteAction implements Action {

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
		
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		//request.setCharacterEncoding("utf-8");
		BoardVo vo = new BoardVo();

		String title= request.getParameter("title");
		String contents = request.getParameter("content");
		Long hit = 0L;
		String regDate = now.format(formatter).toString();
		Long gNo = vo.getgNo() + 1;
		Long oNo = vo.getoNo() + 1;
		

		UserVo userVo = new UserVo();

		
	   
		vo.setTitle(title);
		vo.setContents(contents);
	
		vo.setUserNo(authUser.getNo());

		
		new BoardRepository().insert(vo);
		response.sendRedirect(request.getContextPath() + "/board");	
		
		WebUtil.forward(request, response,"board/");


	}

}
