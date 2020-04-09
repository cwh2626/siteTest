package com.how.cwh.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.how.cwh.vo.Member;

@Repository
public class MemberDAO {

	@Autowired
	SqlSession sqlSession;
	
	public int insert(Member member) {
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		
		int result = mapper.insert(member);
		
		return result;
	}
	
	public Member login(String email) {
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		
		Member result = mapper.login(email);
		
		return result;
	}
}
