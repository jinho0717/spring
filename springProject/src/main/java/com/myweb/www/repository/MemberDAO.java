package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.security.AuthVO;
import com.myweb.www.security.MemberVO;

public interface MemberDAO {

	int register(MemberVO mvo);

	int insertAuthInit(String email);

	MemberVO selectEmail(String username);

	List<AuthVO> selectAuths(String username);

	int updateLastLogin(String authEmail);

	List<MemberVO> selectAllList();

	MemberVO selectUser(String email);

	int modify(MemberVO mvo);

	String selectPwd(MemberVO mvo);

	int modifyNick(MemberVO mvo);

	void removeAuth(String email);

	int remove(String email);

}
