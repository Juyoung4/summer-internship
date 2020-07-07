package com.juyoung.dao;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
 
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.juyoung.dto.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO{
	
	@Autowired
    private SqlSession sqlSession;
    
    private static final String Namespace = "com.juyoung.mapper.memberMapper";
    
    @Override
    public List<MemberVO> selectMember() throws Exception {
    	//memberMapper.xml에 등록한 쿼리문 실행[id를 이용하여]
        return sqlSession.selectList(Namespace+".selectMember");
    }
    
    //회원가입을 위한 메소드 구현[mybatis는 insert문 반환값이 0,1]
    public int join(MemberVO memberVO) {
    	int cnt = 0;
    	cnt = sqlSession.insert(Namespace+".insertMember", memberVO);
    	return cnt;
    }
    
    //로그인을 구현 구현 메소드
    public MemberVO login(Map<String, String> map) {
    	MemberVO vo = new MemberVO();
    	vo = sqlSession.selectOne(Namespace+".loginMember",map);
    	return vo;
    }
}
