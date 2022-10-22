package com.example.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import com.example.persistence.BlogReplyRepository;
import com.example.persistence.BlogRepository;

@Configuration
public class BlogConfig {
	
	private final BlogRepository repo;
	private final BlogReplyRepository replyrepo;
	
	public BlogConfig(BlogRepository repo,BlogReplyRepository replyrepo) {
		this.repo = repo;
		this.replyrepo = replyrepo;
	}
	
	@Bean
	public BlogServiceImpl service() {
		return new BlogServiceImpl(repo, replyrepo);
		
		
	}


	
	
	

		
	

}
