package com.example.demo;


import com.example.demo.Model.UsersList;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@MapperScan("com.example.demo.Dao")
public class DemoApplication {
	public static void main(String[] args) {
		UsersList.userlist.put("testname", "testpassword");
		SpringApplication.run(DemoApplication.class, args);
	}

}
