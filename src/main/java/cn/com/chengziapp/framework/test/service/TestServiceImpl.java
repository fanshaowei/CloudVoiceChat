package cn.com.chengziapp.framework.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.chengziapp.framework.test.bean.Test;
import cn.com.chengziapp.framework.test.mapper.TestMapper;

@Service("testService")
public class TestServiceImpl implements TestService{

	@Autowired
	TestMapper testMapper;
	
	@Override
	public List<Test> findTestList() {
		return testMapper.findTestList();
	}

}
