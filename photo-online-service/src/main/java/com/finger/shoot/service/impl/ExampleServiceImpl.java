package com.finger.shoot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finger.shoot.mapper.ExampleMapper;
import com.finger.shoot.service.ExampleService;

@Service
public class ExampleServiceImpl implements ExampleService {
	
	@Autowired
	private ExampleMapper exampleMapper;


}
