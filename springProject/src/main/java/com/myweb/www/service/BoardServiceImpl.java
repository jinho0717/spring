package com.myweb.www.service;

import static org.hamcrest.CoreMatchers.is;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.FileVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.repository.BoardDAO;
import com.myweb.www.repository.CommentDAO;
import com.myweb.www.repository.FileDAO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardServiceImpl implements BoardService {
	
	@Inject
	private BoardDAO bdao;

	@Inject
	private FileDAO fdao;
	
	@Inject
	private CommentDAO cdao;
//	@Override
//	public int register(BoardVO bvo) {
//		
//		return bdao.register(bvo);
//	}

	@Override
	public List<BoardVO> getList(PagingVO pagingVO) {
		// 전체 게시글 가져올 때, 댓글 수, 파일수 update
		// int isOk = bdao.updateCommentCount();
		// isOk *= updateFileCount();
		return bdao.getList(pagingVO);
	}

	@Transactional
	@Override
	public BoardVO getDetail(long bno) {
		
		bdao.readCount(bno,1);
		return bdao.getDetail(bno);
	}
	@Transactional
	@Override
	public int modify(BoardVO bvo) {
		bdao.readCount(bvo.getBno(),-2);
		return bdao.modify(bvo);
	}

	@Override
	public int remove(long bno) {
		// 게시글 삭제시 파일, 댓글 같이삭제
		//댓글 삭제
		//int isOk = cdao.removeCommentAll(bno);
		// isOk * fado.removeFileAll(bno);
		return bdao.remove(bno);
	}

	@Override
	public int getTotalCount(PagingVO pagingVO) {
		
		return bdao.getTotalCount(pagingVO);
	}

	@Transactional
	@Override
	public int register(BoardDTO bdto) {
		// bvo, flist 가져와서 각자 db에 저장
		int isUp =bdao.register(bdto.getBvo()); //bno 등록 
		if (bdto.getFlist()==null) {
			isUp *=1;
			return isUp;
		}
		//bvo insert 후, 파일도 있다면 
		if (isUp > 0 && bdto.getFlist().size() > 0) {
			long bno = bdao.selectOneBno(); //가장 마지막에 등록된 bno
			//모든 파일에 bno set
			for(FileVO fvo : bdto.getFlist()) {
				fvo.setBno(bno);
				isUp *= fdao.insertFile(fvo);
			}
		}
		return isUp;
	}

	@Transactional
	@Override
	public BoardDTO getDetailFile(long bno) {
		
		bdao.readCount(bno,1);
		BoardDTO bdto = new BoardDTO();
		bdto.setBvo(bdao.getDetail(bno));
		bdto.setFlist(fdao.getFileList(bno));
		log.info("aaaaa>> "+bdto.getFlist());
		return bdto;
	}

	@Override
	public int fileRemove(String uuid) {
		// TODO Auto-generated method stub
		return fdao.fileRemove(uuid);
	}
	@Transactional
	@Override
	public int modifyFile(BoardDTO bdto) {
		bdao.readCount(bdto.getBvo().getBno(),-2);
		int isOk = bdao.modify(bdto.getBvo());
		if (bdto.getFlist() == null) {
			isOk *=1;
		}else {
			if (isOk > 0 && bdto.getFlist().size()>0) {
				long bno = bdto.getBvo().getBno();
				for(FileVO fvo : bdto.getFlist()) {
					fvo.setBno(bno);
					isOk *= fdao.insertFile(fvo);
				}
			}
		}
		return isOk;
	}

	@Override
	public int fcnt() {
		// TODO Auto-generated method stub
		return bdao.fcnt();
	}

	@Override
	public int ccnt() {
		// TODO Auto-generated method stub
		return bdao.ccnt();
	}

	
	@Override
	public int filedel(long bno) {
		// TODO Auto-generated method stub
		return fdao.del(bno);
	}
	
	
	@Override
	public int commentdel(long bno) {
		// TODO Auto-generated method stub
		return cdao.del(bno);
	}
	
}
