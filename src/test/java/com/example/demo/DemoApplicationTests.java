package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
class DemoApplicationTests {
	private MockMvc mvc;

	@BeforeTestMethod
	public void setUp() throws Exception{
	}
	@Test
	void contextLoads() {
	}

}
