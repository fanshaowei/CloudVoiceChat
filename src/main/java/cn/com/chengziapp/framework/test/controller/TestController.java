package cn.com.chengziapp.framework.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.com.chengziapp.framework.redis.RedisUtil;
import cn.com.chengziapp.framework.test.bean.Test;
import cn.com.chengziapp.framework.test.service.TestService;

@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	TestService testService;
	
	@Autowired
	RedisUtil redisUtil;
	
	@RequestMapping("getAllTest")
	public List<Test> getAllTest(){
		redisUtil.setValue("1", "hello", -1);
		
		return this.testService.findTestList();
	}

}
