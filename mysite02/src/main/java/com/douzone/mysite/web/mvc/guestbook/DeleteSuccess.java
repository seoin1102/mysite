package com.douzone.mysite.web.mvc.guestbook;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.douzone.mysite.repository.GuestbookRepository;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.WebUtil;
import com.douzone.mysite.vo.GuestbookVo;
public class DeleteSuccess implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String no2 = request.getParameter("no");
		String password = request.getParameter("password");
		
		GuestbookVo vo = new GuestbookVo();
		long no= Long.parseLong(no2);
		vo.setNo(no);
		vo.setPassword(password);

		new GuestbookRepository().delete(vo);
		
		WebUtil.forward(request, response, "guestbook/list");

	}

}
