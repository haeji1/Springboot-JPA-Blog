package com.cos.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//스프링이 com.cos.blog패키지 이하를 스캔해서
//특정 어노테이션이 붙어있는 클래스 파일 new해서 (IOC)스프링 컨테이너에 관리
@RestController
public class BlogControllerTest {

	@GetMapping("/test/hello")
	public String hello() {
		return "<h1>hello spring boot</h1>";
	}
}
