package com.example;

import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.domain.Blog;
import com.example.persistence.BlogRepository;

@ExtendWith(SpringExtension.class)
@Commit
@Log
@SpringBootTest
public class BlogRepositoryTests {
	
	@Autowired
	BlogRepository repo;
	
	/*@Test
	public void testList1() {
		Pageable pageable = PageRequest.of(0,10,Direction.DESC,"bno");
		
		Page<Blog> result = repo.findAll(
				repo.makePredicate("t", "1"),pageable);
		log.info("=============");
		
		log.info("PAGE :" + result.getPageable());
		result.getContent().forEach(blog->log.info(""+blog));
	}*/
	@Test
	public void insertBoardDummies() {
		for(int i=110;i<170;i++) {
			Blog blog = new Blog();
			
			blog.setTitle("title"+(i+110));
			blog.setContent("content"+(i+110));
			blog.setWriter("admin");
			blog.setCategory("category8");
			
			repo.save(blog);
		}
 	}
	


}
