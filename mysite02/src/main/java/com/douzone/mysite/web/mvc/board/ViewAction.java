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

public class ViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		String no2 = request.getParameter("no");
		long no= Long.parseLong(no2);

		BoardVo list = new BoardRepository().findByNo(no);
		request.setAttribute("list",list);
		// 접근제어/ 로그인하고 들어오도록 메인으로 보내버림.
		/////////////////////////////////////////////////////////////////////
		if(session == null) {
			WebUtil.redirect(request, response, request.getContextPath());
			return;
		}
		
		long hit = list.getHit()+1l;
		System.out.println(hit);
		session.setAttribute("hit", hit);
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		BoardVo vo = new BoardVo();
		vo.setTitle(list.getTitle());
		vo.setContents(list.getContents());
		vo.setNo(no);
		vo.setHit(hit);
		new BoardRepository().modify(vo);
		
		if(authUser == null) {

			WebUtil.forward(request, response, "board/view");
			return;
			}
		/////////////////////////////////////////////////////////////////////

		UserVo userVo = new UserRepository().findByNo(authUser.getNo());
		request.setAttribute("userVo", userVo);
		

		WebUtil.forward(request, response, "board/view");

	}

}
