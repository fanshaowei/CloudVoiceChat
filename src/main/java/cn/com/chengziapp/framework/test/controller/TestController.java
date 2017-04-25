package cn.com.chengziapp.framework.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.com.chengziapp.framework.test.bean.Test;
import cn.com.chengziapp.framework.test.service.TestService;

@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	TestService testService;
	
	@RequestMapping("getAllTest")
	public List<Test> getAllTest(){
		return this.testService.findTestList();
	}

}
