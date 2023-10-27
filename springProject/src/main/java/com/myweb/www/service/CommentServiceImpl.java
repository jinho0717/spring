package com.myweb.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.repository.CommentDAO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

	@Inject
	private CommentDAO cdao;

	@Override
	public int insert(CommentVO cvo) {
		
		return cdao.insert(cvo);
	}

//	@Override
//	public List<CommentVO> getList(long bno) {
//		
//		return cdao.getList(bno);
//	}

	@Override
	public int delete(int cno) {
		
		return cdao.delete(cno);
	}

	@Override
	public int update(CommentVO cvo) {
		
		return cdao.update(cvo);
	}
	
	@Transactional
	@Override
	public PagingHandler getList(long bno, PagingVO pgvo) {
		//totalCount
		int totalCount = cdao.selectOneBnoTotalCount(bno);
		//Comment List
		List<CommentVO> list = cdao.selectListPaging(bno, pgvo);
		PagingHandler ph = new PagingHandler(pgvo, totalCount , list);
		//pagingHandler 값 완성 리턴
		return ph;
	}

	
	
}
