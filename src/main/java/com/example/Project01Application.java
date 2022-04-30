package com.example;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//import com.example.persistence.CustomBlogRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;

@EnableJpaAuditing
@SpringBootApplication
public class Project01Application {

	public static void main(String[] args) {
		SpringApplication.run(Project01Application.class, args);
	}
	
	@Bean
	public JPAQueryFactory jpaQueryFactory(EntityManager em){
		return new JPAQueryFactory(em);
	}

}
