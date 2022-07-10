package com.douzone.mysite.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.mysite.dto.JSONResult;
import com.douzone.mysite.service.GuestbookService;
import com.douzone.mysite.vo.GuestbookVo;



@RestController("guestbookApiController")
@RequestMapping("/api/guestbook")
public class GuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	@PostMapping("/add-form")
	public JSONResult ex01(@RequestBody GuestbookVo vo) {
		guestbookService.addMessage(vo);

		return JSONResult.success(vo);
	}
	
	@GetMapping("")
	public JSONResult ex02() {
		List<GuestbookVo>list = guestbookService.getMessagelist();
		return JSONResult.success(list);
	}
	
	@DeleteMapping("/delete-form")
	public JSONResult ex03(@RequestBody GuestbookVo vo) {
		Long no = vo.getNo();
		String password = vo.getPassword();
		Boolean result = guestbookService.deleteMessage(no, password);
		System.out.println(result);
		return JSONResult.success(result?no:-1);
	}
	
	@GetMapping("/startNo")
	public JSONResult ex02(@RequestParam(value="no", required=true, defaultValue="")long no) {
		List<GuestbookVo>list = guestbookService.getMessagelist(no);
		System.out.println(no);
		System.out.println(list);
		return JSONResult.success(list);
	}
}
