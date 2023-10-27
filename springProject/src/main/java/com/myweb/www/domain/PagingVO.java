package com.myweb.www.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
@ToString
public class PagingVO {

	private int pageNo; //페이지수
	private int qty; //게시글수
	private String type; //검색처리용
	private String keyword; 

	
	public PagingVO() {
		this(1, 10);
	}

	public PagingVO(int pageNo, int qty) {
		this.pageNo = pageNo;
		this.qty = qty;
	}
	
	//Limit 시작, qty :  시작 페이지 번호 구하기
	//pageNo 1 2 3 4 
	//0,10 /10,10 /20,10 
	public int getPageStart() {
		return (this.pageNo-1)*qty;
	}
	
	//타입의 형태를 여러가지 형태로 복합적인 검색읋 하기위해서
	//타입의 키워드 t,c,w,tc,tw,cw,tcw
	//복함타입을 배열로 나누기위해 사용
	public String[] getTypeToArray() {
		return this.type == null ? new String[] {} : this.type.split("");
	}
	
	
}
