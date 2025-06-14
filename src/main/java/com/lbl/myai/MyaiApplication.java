package com.lbl.myai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.lbl.myai.mapper")
@SpringBootApplication
public class MyaiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyaiApplication.class, args);
	}

}
