package com.example.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
