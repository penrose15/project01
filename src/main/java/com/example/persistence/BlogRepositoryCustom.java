package com.example.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;


public interface BlogRepositoryCustom{
	
	public Page<Object[]> search(String type, String keyword, Pageable pageable);
	
	public Page<Object[]> viewPage(Pageable pageable);

}
