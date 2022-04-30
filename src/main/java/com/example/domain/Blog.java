package com.example.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude="replies")
@Entity
@Table(name = "blog")
@EqualsAndHashCode(of="bno")
public class Blog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bno;
	
	@Column(length=50)
	private String category;
	
	@Column(length=50)
	private String title;
	
	@Column(length=50)
	private String writer;
	
	@Column(length=2000)
	private String content;
	
	@CreatedDate
	private LocalDateTime regdate;
	@LastModifiedDate
	private LocalDateTime updatedate;
	
	@JsonManagedReference
	@OneToMany(mappedBy="blog", fetch=FetchType.LAZY)
	private List<BlogReply> replies;

}
