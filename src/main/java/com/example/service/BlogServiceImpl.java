package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.domain.Blog;
import com.example.domain.BlogReply;
import com.example.persistence.BlogReplyRepository;
import com.example.persistence.BlogRepository;
import com.example.persistence.BlogRepositoryCustom;
import com.example.vo.VO;

public class BlogServiceImpl {
	
	private final BlogRepository blogRepository;
	private final BlogReplyRepository blogReplyRepository;
	
	public BlogServiceImpl(BlogRepository blogRepository,BlogReplyRepository blogReplyRepository) {
		this.blogRepository = blogRepository;
		this.blogReplyRepository =blogReplyRepository;
	}
	
	@Transactional
	public void del(Blog blog, Long bno) {
		List<Long> list = blogReplyRepository.getRno(blog);
		
		if(list.size() != 0) {
			for(int i=0; i<list.size();i++) {
				blogReplyRepository.deleteById(list.get(i));
			}
			blogRepository.deleteById(bno);
			
		}
		
			
		
	}

	

}
