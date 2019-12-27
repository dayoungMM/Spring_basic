package com.multicampus.basic.controller;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RestTemplateController {
	String KAKAO_KEY  = System.getenv(".env");
	String X_NAVER_CLIENT_ID = System.getenv(".env");
	String X_NAVER_CLIENT_SECRET = System.getenv(".env");
	@GetMapping("/getString")
//	//String 형식으로 받아오기
//	public String getString() {
//		RestTemplate rt = new RestTemplate();
//		String result = rt.getForObject("http://ggoreb.com/http/json1.jsp", String.class);

	public Map<String, Object> getString() {
		RestTemplate rt = new RestTemplate();
		Map result = rt.getForObject("http://ggoreb.com/http/json1.jsp", Map.class);
		return result;
	}

	@GetMapping("/getKakao")
	public ResponseEntity<Map> getKakao(
			@RequestParam("address") String address) {
		RestTemplate rt = new RestTemplate();
		RequestEntity requestEntity = null;
		try {
			requestEntity = RequestEntity
					.get(new URI("https://dapi.kakao.com/v2/local/search/address.json?query="
							+ URLEncoder.encode(address, "utf-8")))
					.header("Authorization", "KakaoAK "+ KAKAO_KEY).build();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		ResponseEntity<Map> entity = rt.exchange(requestEntity, Map.class);
		return entity;
	}

	@GetMapping("/getNaver")
	 public ResponseEntity<Map> getNaver(
			 @RequestParam("translate") String translate) {
	 RestTemplate rt = new RestTemplate();
	 RequestEntity<Map<String, String>> requestEntity = null;
	 try {
	 Map<String, String> body = new HashMap<>();
	 body.put("source", "ko");
	 body.put("target", "en");
	 body.put("text", translate);
	 requestEntity = RequestEntity.post(
	 new URI("https://openapi.naver.com/v1/papago/n2mt"))
	 .header("X-Naver-Client-Id", X_NAVER_CLIENT_ID)
	 .header("X-Naver-Client-Secret", X_NAVER_CLIENT_SECRET )
	 .body(body);
	 } catch (URISyntaxException e) {
	 e.printStackTrace();
	 }
	 ResponseEntity<Map> entity = rt.exchange(requestEntity, Map.class);
	 return entity;
}
}