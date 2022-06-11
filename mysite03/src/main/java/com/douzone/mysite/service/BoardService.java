package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardrepository;
	public List<BoardVo> getIndex() {
		return boardrepository.findAll();
	}
	public BoardVo getView(long no) {

		return boardrepository.findByNo(no);
	}
	
	public boolean deleteMessage(Long gNo, Long oNo, Long userNo) {
		return boardrepository.delete(gNo, oNo, userNo);
	}
	
	public boolean write(BoardVo vo) {
		
		return boardrepository.insert(vo);

	}
	public boolean update(BoardVo vo) {
		return boardrepository.update(vo);
	}
	
	public boolean updateHit(BoardVo vo) {
		return boardrepository.updateHit(vo);
	}

}
