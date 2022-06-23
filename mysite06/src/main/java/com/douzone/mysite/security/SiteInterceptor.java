package com.douzone.mysite.security;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.SiteVo;
import com.douzone.mysite.vo.UserVo;

public class SiteInterceptor implements HandlerInterceptor {
	
	@Autowired
	private SiteService siteService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		ServletContext  application = request.getServletContext();
		if(application.getAttribute("site") == null) {
		SiteVo siteVo = siteService.getSite();
		
		application.setAttribute("site", siteVo);
		response.sendRedirect(request.getContextPath());
		return false;

		}
		return true;

	}
}