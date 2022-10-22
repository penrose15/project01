package com.example.domain;

import com.example.domain.Blog;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "blog_replies")
@EqualsAndHashCode(of = "rno")
@ToString(exclude="blog")
public class BlogReply {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rno;
	
	@Column(length=200)
	private String replyText;
	
	@Column(length=50)
	private String replyer;
	
	@CreatedDate
	private LocalDateTime regdate;
	@LastModifiedDate
	private LocalDateTime updatedate;
	
	@JsonBackReference
	@ManyToOne(fetch=FetchType.LAZY)
	private Blog blog;
}
