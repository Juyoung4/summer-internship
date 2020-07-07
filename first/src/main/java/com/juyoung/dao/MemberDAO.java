package com.juyoung.dao;

import java.util.List;

import com.juyoung.dto.MemberVO;

public interface MemberDAO {
	//먼저 memberDAO 인터페이스만 작성
	//구현은 MemberDAOImpl 클래스에서 함
	public List<MemberVO> selectMember() throws Exception;
	//회원가입을 위한 메소드
	public int join(MemberVO memberVO);
}
