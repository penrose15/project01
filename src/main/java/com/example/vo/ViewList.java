package com.example.vo;


import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;

@Getter
@ToString
@Log

public class ViewList<T> {
	
	private Page<T> result;
	private int currentBnoNum;
	private Pageable currentBno;
	private List<Pageable> bnoList;
	
	public ViewList(Page<T> result) {
		this.result = result;
		
		calcPages();
	}
	private void calcPages() {
		int tempEndNum = (int)(Math.ceil(this.currentBnoNum/10.0)*10);
		int startNum = tempEndNum-9;
	}

}
