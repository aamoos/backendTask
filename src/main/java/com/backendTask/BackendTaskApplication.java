package com.backendTask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import static org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO;

@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO)		//spring boot 3.3부터 직렬화 관련 부분 추가됬음 이부분 추가해서 빼는게 낫음
public class BackendTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendTaskApplication.class, args);
	}
}