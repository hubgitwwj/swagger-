package com.example.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "用户信息查询", description = "用户基本信息操作API", tags = "UserApi", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class HelloController {



		@ApiOperation(value="测试接口", notes="测试接口")
		@PostMapping("hello")
		public String hello() {
			return "hello";
		}
		
		@ApiOperation(value="测试接口", notes="测试接口")
		@PostMapping("hello2")
		public String hello2() {
			return "hello2";
		}
		
	

}
