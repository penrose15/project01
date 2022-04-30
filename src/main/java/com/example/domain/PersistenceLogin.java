package com.example.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Table(name = "persistent_logins")
@Entity
@Getter
@Setter
public class PersistenceLogin {
	//쿠키값을 사용하기 위한 엔터티
	 @Id
	    @Column(length = 64)
	    private String series;

	    @Column(nullable = false, length = 64)
	    private String username;

	    @Column(nullable = false, length = 64)
	    private String token;

	    @Column(name = "last_used", nullable = false, length = 64)
	    private LocalDateTime lastUsed;

}
