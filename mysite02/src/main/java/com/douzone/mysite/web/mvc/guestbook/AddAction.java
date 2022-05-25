package com.douzone.mysite.web.mvc.guestbook;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.GuestbookRepository;
import com.douzone.mysite.vo.GuestbookVo;
import com.douzone.web.mvc.Action;

public class AddAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		request.setCharacterEncoding("utf-8");
		String name= request.getParameter("name");
		String password = request.getParameter("password");
		String message = request.getParameter("message");
		String datetime = now.format(formatter).toString();
	   
		GuestbookVo vo = new GuestbookVo();
		vo.setName(name);
		vo.setPassword(password);
		vo.setMessage(message);
		vo.setDatetime(datetime);
		
		new GuestbookRepository().insert(vo);
		response.sendRedirect(request.getContextPath() + "/guestbook?a=");	
	}

}
