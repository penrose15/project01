package com.example.controller;

import com.example.domain.Blog;
import com.example.domain.BlogReply;
import com.example.persistence.BlogReplyRepository;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import lombok.extern.java.Log;

@RestController
@RequestMapping("/replies/*")
@Log
public class ReplyController {
	
	@Autowired
	private BlogReplyRepository repo;
	
	@Secured(value= {"ROLE_BASIC", "ROLE_ADMIN"})
	@Transactional
	@PostMapping("/{bno}")
	public ResponseEntity<List<BlogReply>> addReply(@PathVariable("bno")Long bno, @RequestBody BlogReply reply){
		Blog blog = new Blog();
		blog.setBno(bno);
		reply.setBlog(blog);
		repo.save(reply);
		
		return new ResponseEntity<>(getListByBlog(blog), HttpStatus.CREATED);
	}
	
	private List<BlogReply> getListByBlog(Blog blog) throws RuntimeException{
		return repo.getRepliesOfBlog(blog);
	}
	
	@Secured(value= {"ROLE_BASIC", "ROLE_ADMIN"})
	@Transactional
	@DeleteMapping("/{bno}/{rno}")
	public ResponseEntity<List<BlogReply>> remove( @PathVariable("bno")Long bno, @PathVariable("rno")Long rno){
		repo.deleteById(rno);
		
		Blog blog = new Blog();
		blog.setBno(bno);
		
		return new ResponseEntity<>(getListByBlog(blog), HttpStatus.OK);
	}
	
	@Secured(value= {"ROLE_BASIC", "ROLE_ADMIN"})
	@Transactional
	@PutMapping("/{bno}")
	public ResponseEntity<List<BlogReply>> modify(@PathVariable("bno") Long bno, @RequestBody BlogReply reply) {
		repo.findById(reply.getRno()).ifPresent(a -> {
			a.setReplyText(reply.getReplyText());
			repo.save(a);
		});
		Blog blog = new Blog();
		blog.setBno(bno);
		
		return new ResponseEntity<>(getListByBlog(blog), HttpStatus.CREATED);
	}
	
	@GetMapping("/{bno}")
	public ResponseEntity<List<BlogReply>> getReplies(@PathVariable("bno")Long bno){
		Blog blog = new Blog();
		blog.setBno(bno);
		
		return new ResponseEntity<>(getListByBlog(blog), HttpStatus.OK);
	}
	
}
