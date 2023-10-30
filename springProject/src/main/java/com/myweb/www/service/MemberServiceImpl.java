package com.myweb.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.myweb.www.repository.MemberDAO;
import com.myweb.www.security.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService {
	
	@Inject
	private MemberDAO mdao;

	@Override
	public int register(MemberVO mvo) {
		int isOk = mdao.register(mvo);
		return mdao.insertAuthInit(mvo.getEmail());
	}

	@Override
	public boolean updateLastLogin(String authEmail) {
		
		return mdao.updateLastLogin(authEmail) > 0 ? true : false;
	}

	@Override
	public List<MemberVO> selectAllList() {
		// TODO Auto-generated method stub
		return mdao.selectAllList();
	}
}
