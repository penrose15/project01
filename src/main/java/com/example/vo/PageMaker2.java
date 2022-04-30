package com.example.vo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;

@Getter
@ToString(exclude="pageList")
@Log
public class PageMaker2<T> {
	
	private Page<T> result;
	
	private Pageable prev;
	private Pageable next;
	private Pageable nextBno;
	private Pageable prevBno;
	
	private int currentPageNum;
	private int totalPageNum;
	private int currentBnoNum;
	private int totalBnoNum;
	
	private Pageable currentBno;
	
	private Pageable currentpage;
	
	private List<Pageable> pageList;
	private List<Pageable> bnoList;
	
	public PageMaker2(Page<T> result) {
		this.result = result;
		this.currentpage = result.getPageable();
		this.currentPageNum = currentpage.getPageNumber()+1;
		this.totalPageNum = result.getTotalPages();
		this.pageList = new ArrayList<>();
		
		this.currentBno = result.getPageable();
		this.currentBnoNum = currentBno.getPageNumber()+1;
		this.totalBnoNum = result.getTotalPages();
		this.bnoList = new ArrayList<>();
		
		
		calcPages();
		calcBnoPages();
	}
	
	private void calcPages() {
		int tempEndNum = (int)(Math.ceil(this.currentPageNum/10.0)*10);
		int startNum = tempEndNum-9;
		
		Pageable startPage = this.currentpage;
		
		//move to start Pageable
		for(int i = startNum; i<this.currentPageNum;i++) {
			startPage = startPage.previousOrFirst();
		}
		if(startPage.getPageNumber()<=0) {
			this.prev = null;
		}
		else {
			this.prev = startPage.previousOrFirst();
		}
		
		if(this.totalPageNum<tempEndNum) {
			tempEndNum = this.totalPageNum;
			this.next = null;
		}
		
		for(int i=startNum; i<=tempEndNum; i++) {
			pageList.add(startPage);
			startPage = startPage.next();
			this.next = startPage.getPageNumber() +1<totalPageNum ? startPage: null;
		}
	}
	private void calcBnoPages() {
		int endNum = currentBnoNum+1;
		int startNum = endNum-2;
		
		Pageable startPage = currentBno;
		
		for(int i = startNum; i<this.currentBnoNum;i++) {
			startPage = startPage.previousOrFirst();
		}
		if(startPage.getPageNumber()<=0) {
			this.prevBno = null;
		}
		else {this.prevBno = startPage.previousOrFirst();
	}
		if(totalBnoNum<endNum) {
			endNum = this.totalBnoNum;
			this.nextBno = null;
		}
		for(int i=startNum; i<endNum;i++) {
			bnoList.add(startPage);
			startPage = startPage.next();
			this.nextBno = startPage.getPageNumber()<totalBnoNum ? startPage:null;
		}
		

}
}
