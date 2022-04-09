package com.example.demo;

import com.example.demo.Users.UsersList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		UsersList.userlist.put("testname", "testpassword");
		SpringApplication.run(DemoApplication.class, args);
	}

}
