package com.multicampus.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class serviceController {
	@GetMapping("/kakao")
	public String kakao() {
		return "kakao";
	}
	
	@GetMapping("/papago")
	public String papago() {
		return "papago";
	}
}
