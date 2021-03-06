package com.silentEight.homeworkDemo;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HomeworkDemoApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(HomeworkDemoApplication.class, args);
		
		/*
		var resource = ResourceUtils.getURL("classpath:static/maleTokens.txt");
		new BufferedReader( new InputStreamReader(resource.openStream()) ).lines().forEach(System.out::println);
		*/
	}

}
