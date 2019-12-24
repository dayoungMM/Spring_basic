package com.multicampus.basic.model;

import lombok.Data;

@Data //lombok 에서 getter setter없이 데이터 불러올 수 있음
public class Member {
	private String name;
	private String userId;
	private String userPassword;
	
}


