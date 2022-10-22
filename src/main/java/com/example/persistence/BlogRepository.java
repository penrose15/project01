package com.example.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.Blog;

@Repository
public interface BlogRepository  extends CrudRepository<Blog, Long>, BlogRepositoryCustom {
	
	

	

	



}
