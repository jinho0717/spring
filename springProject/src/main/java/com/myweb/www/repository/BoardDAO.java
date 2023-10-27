package com.myweb.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;

public interface BoardDAO {

	int register(BoardVO bvo);

	List<BoardVO> getList(PagingVO pagingVO);

	BoardVO getDetail(long bno);

	int modify(BoardVO bvo);

	void readCount(@Param("bno") long bno , @Param("cnt") int cnt);

	int remove(long bno);

	int getTotalCount(PagingVO pagingVO);

	long selectOneBno();

	int fcnt();

	int ccnt();

	

}
