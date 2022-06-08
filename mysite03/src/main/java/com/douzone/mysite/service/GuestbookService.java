package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.repository.GuestbookRepository;
import com.douzone.mysite.vo.GuestbookVo;

@Service
public class GuestbookService {
	@Autowired
	private GuestbookRepository guestbookRepository;
	public List<GuestbookVo> getMessagelist(){
		return guestbookRepository.findAll();
	
	}
	
	public boolean deleteMessage(Long no, String password) {
		return guestbookRepository.delete(no, password);
	}
	
	public boolean addMessage(GuestbookVo vo) {
		return guestbookRepository.insert(vo);
	}
}
