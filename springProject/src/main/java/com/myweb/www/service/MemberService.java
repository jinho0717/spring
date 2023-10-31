package com.myweb.www.service;


import java.util.List;

import com.myweb.www.security.MemberVO;

public interface MemberService {

	int register(MemberVO mvo);

	boolean updateLastLogin(String authEmail);

	List<MemberVO> selectAllList();

	MemberVO getDetail(String email);

	int modify(MemberVO mvo);

	String selectpwd(MemberVO mvo);

	int modifyNick(MemberVO mvo);

	int remove(String email);

	

}
