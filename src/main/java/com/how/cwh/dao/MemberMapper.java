package com.how.cwh.dao;

import com.how.cwh.vo.Member;

public interface MemberMapper {
	public int insert(Member member);
	
	public Member login(String email);



}
