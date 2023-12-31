package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;

public interface BoardService {

//	int register(BoardVO bvo);

	List<BoardVO> getList(PagingVO pagingVO);

	BoardVO getDetail(long bno);

	int modify(BoardVO bvo);

	int remove(long bno);

	int getTotalCount(PagingVO pagingVO);

	int register(BoardDTO boardDTO);

	BoardDTO getDetailFile(long bno);

	int fileRemove(String uuid);

	int modifyFile(BoardDTO bdto);

	int fcnt();

	int ccnt();

	int filedel(long bno);

	int commentdel(long bno);

}
