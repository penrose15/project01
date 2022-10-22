package com.example.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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
	@OneToMany(mappedBy="blog")
	private List<BlogReply> replies;

}
