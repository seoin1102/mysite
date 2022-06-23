package com.douzone.mysite.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.service.FileUploadService;
import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;

@Auth(role="ADMIN")
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private SiteService siteService;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@RequestMapping({"","/main"})
	public String main(Model model) {
		SiteVo vo = siteService.getSite();
		model.addAttribute("site", vo);
		return "admin/main";
	}
	
	@RequestMapping(value="/main/update" ,method=RequestMethod.POST)
	public String update(
		@RequestParam("title") String title,	
		@RequestParam("welcomeMessage") String welcomeMessage,
		@RequestParam("description") String description,
		SiteVo vo,
		@RequestParam("file") MultipartFile multipartFile,
		Model model) {
		
		String url = fileUploadService.restoreImage(multipartFile);
		vo.setTitle(title);
		vo.setWelcomeMessage(welcomeMessage);
		vo.setProfileURL(url);
		vo.setDescription(description);
		siteService.updateSite(vo);
		model.addAttribute("site", url);
		System.out.println(url);
		return "redirect:/admin";
	}

	@RequestMapping("/guestbook")
	public String guestbook() {
		return "admin/guestbook";
	}
	
	@RequestMapping("/board")
	public String board() {
		return "admin/board";
	}
	
	@RequestMapping("/user")
	public String user() {
		return "admin/user";
	}
	
}