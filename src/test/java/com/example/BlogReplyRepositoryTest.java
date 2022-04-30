package com.example;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.domain.Blog;
import com.example.domain.BlogReply;
import com.example.persistence.BlogReplyRepository;
//import com.example.persistence.BlogRepository;

//import groovyjarjarantlr4.runtime.IntStream;

@SpringBootTest
@Commit
@ExtendWith(SpringExtension.class)
public class BlogReplyRepositoryTest {
	
	@Autowired
	BlogReplyRepository repo;
	
	@Test
	public void testInsertReplies() {
		//given
		Blog blog = new Blog();
		blog.setBno(168L);
		//when
		BlogReply reply = new BlogReply();
		reply.setBlog(blog);
		reply.setReplyer("antiego");
		reply.setReplyText("wake up From myth");
		repo.save(reply);
		//then
	}
//	@Test
//	public void deleteReplies() {
//		//given
//		Blog blog = new Blog();
//		blog.setBno(168L);
//		//when
//		List<Long> list = repo.getRno(blog);
//		//then
//		System.out.println(list);
//		if(list.size() != 0) {
//			for(int i=0; i<list.size();i++) {
//				repo.deleteById(list.get(i));
//				System.out.println("삭제되었습니다.");
//				}
//			}
//	}
	
	@Test
	public void modifyReplies() {
		BlogReply reply = new BlogReply();
		Blog blog = new Blog();
		blog.setBno(168L);
		List<BlogReply> b = repo.getRepliesOfBlog(blog);
		System.out.println(b);
		
		repo.findById(168L).ifPresent(origin -> {
			origin.setReplyText("이제 난 변신");
			repo.save(origin);
		});
		Blog blog1 = new Blog();
		blog1.setBno(168L);
		List<BlogReply> a = repo.getRepliesOfBlog(blog1);
		System.out.println(a);
		
	}

}
