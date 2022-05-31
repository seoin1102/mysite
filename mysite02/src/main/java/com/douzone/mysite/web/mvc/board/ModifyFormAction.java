package com.douzone.mysite.web.mvc.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.WebUtil;

public class ModifyFormAction implements Action {

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
		
	
		//request.setCharacterEncoding("utf-8");
		System.out.println(request.getParameter("title"));
		System.out.println(request.getParameter("content"));
		String title= request.getParameter("title");
		String contents = request.getParameter("content");
		
		BoardVo vo = new BoardVo();
		
		String no2 = request.getParameter("no");
		System.out.println("확인"+no2);
		long no= Long.parseLong(no2);
		
		vo.setTitle(title);
		vo.setContents(contents);
		vo.setNo(no);
	
		vo.setUserNo(authUser.getNo());

		List<BoardVo> list = new BoardRepository().modify(vo);
		request.setAttribute("list",list);
		WebUtil.forward(request, response,"board/modify");

	}

}
