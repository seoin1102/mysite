package com.douzone.mysite.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.service.GuestbookService;
import com.douzone.mysite.vo.GuestbookVo;


@Controller
@RequestMapping("/guestbook")
public class GuestbookController {
	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(Model model) {
		List<GuestbookVo> list = guestbookService.getMessagelist();
		model.addAttribute("list",list);
		return "guestbook/index";
	}
	
	@RequestMapping(value = "/delete/{no}", method = RequestMethod.GET)
	public String delete(@PathVariable("no") Long no) {
		return "guestbook/delete";
		}
	
	@RequestMapping(value = "/delete/{no}", method = RequestMethod.POST)
	public String delete(@PathVariable("no") Long no,
						@RequestParam(value="password", required=true, defaultValue="")String password) {
		
		guestbookService.deleteMessage(no, password);
		return "redirect:/guestbook";
		}
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public String add(GuestbookVo vo) {
		guestbookService.addMessage(vo);
		return "redirect:/guestbook";
	}
	
//	@ExceptionHandler(Exception.class)
//	public String handlerException() {
//		return "error/exception";
//	}
	
}
