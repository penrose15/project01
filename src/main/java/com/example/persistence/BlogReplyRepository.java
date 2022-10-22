package com.example.persistence;

import com.example.domain.Blog;
import com.example.domain.BlogReply;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface BlogReplyRepository extends CrudRepository<BlogReply, Long>{
	
	@Query("SELECT r FROM BlogReply r WHERE r.blog = ?1 AND r.rno>0 ORDER BY r.rno ASC")
	public List<BlogReply> getRepliesOfBlog(Blog blog);
	
	@Query("SELECT r.rno FROM BlogReply r WHERE r.blog =?1 AND r.rno>0 ORDER BY r.rno ASC")
	public List<Long> getRno(Blog blog);

}
