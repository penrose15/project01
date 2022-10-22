package com.example.persistence;

import com.example.domain.Blog;
import com.example.domain.QBlog;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.ArrayList;
import java.util.List;

public class BlogRepositoryImpl extends QuerydslRepositorySupport implements BlogRepositoryCustom {
	
	public BlogRepositoryImpl() {
		super(Blog.class);

		// TODO Auto-generated constructor stub
	}

	@Override
	public Page<Object[]> search(String type, String keyword, Pageable pageable) {
		
		JPAQueryFactory queryFactory = new JPAQueryFactory(getEntityManager());
//		
//		
		QBlog blog = QBlog.blog;


		
		JPQLQuery<Tuple> query =
		queryFactory.select(blog.bno, blog.category, blog.title,blog.content)
					.from(blog)
					.where(blog.bno.gt(0L))
					.orderBy(blog.bno.desc())
					.groupBy(blog.bno)
					.offset(pageable.getOffset())
					.limit(pageable.getPageSize());
		
		if(type !=null) {
			switch(type.toLowerCase()) {
			case "t" : query.where(blog.title.like("%"+keyword+"%")); break;
			case "c" : query.where(blog.content.like("%"+keyword+"%")); break;
			}
		}
		
		List<Tuple> result = query.fetch();
		List<Object[]> resultList = new ArrayList<>();
		result.forEach(t->{
			resultList.add(t.toArray());
		});
		long total = query.fetchCount();
		
		return new PageImpl<>(resultList, pageable, total);
		
	}

	@Override
	public Page<Object[]> viewPage(Pageable pageable) {
		
		JPAQueryFactory queryFactory = new JPAQueryFactory(getEntityManager());
//		
//		
		QBlog blog = QBlog.blog;


		
		JPQLQuery<Tuple> query =
		queryFactory.select(blog.bno, blog.category, blog.title,blog.content)
					.from(blog)
					.where(blog.bno.gt(0L))
					.orderBy(blog.bno.desc())
					.groupBy(blog.bno)
					.offset(pageable.getOffset())
					.limit(pageable.getPageSize());
		
		
		List<Tuple> result = query.fetch();
		List<Object[]> resultList = new ArrayList<>();
		result.forEach(t->{
			resultList.add(t.toArray());
		});
		long total = query.fetchCount();
		
		return new PageImpl<>(resultList, pageable, total);
	}







}
