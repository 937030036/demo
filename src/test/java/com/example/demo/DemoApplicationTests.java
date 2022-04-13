package com.example.demo;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {
	@Autowired
	DataSource dataSource;

	@Test
	void contextLoads() throws Exception {
		System.out.println("连接为："+dataSource.getConnection());
	}

}
