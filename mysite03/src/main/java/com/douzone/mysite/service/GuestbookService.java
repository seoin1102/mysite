package com.douzone.mysite.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.douzone.mysite.vo.GuestbookVo;

@Service
public class GuestbookService {
	public List<GuestbookVo> getMessagelist(){
		return null;
	}
	
	public boolean deleteMessage(Long no, String password) {
		return false;
	}
	
	public boolean addMessage(GuestbookVo vo) {
		return false;
	}
}
