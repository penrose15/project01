package com.example.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.java.Log;

@Log
public class LoginCheckInterceptor implements HandlerInterceptor{
	
	public Boolean preHandler(HttpServletRequest request, HttpServletResponse response, Object Handler) throws Exception {
		log.info("preHandler...........................");
		String dest = request.getParameter("dest");
		
		if(dest != null) {
			request.getSession().setAttribute("dest", dest);
		}
		
		return true;
	}
	
	

}
