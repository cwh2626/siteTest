package com.how.cwh.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.how.cwh.dao.MemberDAO;
import com.how.cwh.vo.Member;

@RequestMapping("Member")
@Controller
public class MemberController {
	
	@Autowired
	MemberDAO mdao;
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@RequestMapping(value ="singUpMain", method = RequestMethod.GET)
	public String singUpMain() {
		return "Member/singUp";
	}
	
	@RequestMapping(value ="singUp", method = RequestMethod.POST)
	public String singUp(Member member) {
		logger.debug("Meber ==> {}", member);
		int result = mdao.insert(member);
		
		if(result != 0) {
			return "redirect:/Member/loginMain";
		}
		
		return "Member/singUp";
	}
	
	@RequestMapping(value ="loginMain", method = RequestMethod.GET)
	public String loginMain() {
		return "Member/login";
	}
	
	@RequestMapping(value ="login", method = RequestMethod.POST)
	public String login(Member member,HttpSession session) {
		logger.debug("Meber ==> {}", member);
		Member result = mdao.login(member.getEmail());
		
		if(result != null && result.getPassword().equals(member.getPassword())) {
			session.setAttribute("loginNickname", result.getNickname());
			session.setAttribute("loginName", result.getName());
			session.setAttribute("loginEmail", result.getEmail());
		}else {
			return "Member/login";
		}
		return "redirect:/";
	}
	
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}


}
