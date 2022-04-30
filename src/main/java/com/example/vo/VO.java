package com.example.vo;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import lombok.Getter;
import lombok.Setter;


public class VO {
	
	private static final int Default_Size = 10;
	private static final int Default_Max_Size=20;
	
	private int page;  // 페이지 번호
	private int size;  //게시물 수
	private String keyword;
	private String type;

	


	public VO() {
		this.page = 1;
		this.size = Default_Size;
	}
	
	public int getPage() {
		return page;
	}
	
	public void setPage(int page) {
		if(page<0) {
			this.page = 1;
		}
		else {
			this.page = page;
		}
	}
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		if(size<Default_Size || size >Default_Max_Size) {
			this.size = Default_Size;
		}
		else {
			this.size = size;
		}
	}
	
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public Pageable makePageable(String...props) {
		return PageRequest.of(page-1, this.size, Direction.DESC, props);
	}
	

}
