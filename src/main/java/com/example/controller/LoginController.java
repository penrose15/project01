package com.example.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.domain.Member;

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String login(Member member, HttpServletRequest request) {
		String referrer = request.getHeader("Referer");
		request.getSession().setAttribute("prevPage", referrer);
		
		return "login";
		
	}
	
	@PostMapping("/login")
	public String loginPost(Member member) {
		return "redirect:/";
	}
	
	@GetMapping("/accessDenied")
	public void accessDenied() {
		
	}
	
	@GetMapping("/logout")
	public void logout() {
		
	}

}
