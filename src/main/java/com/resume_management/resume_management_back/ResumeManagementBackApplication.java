package com.resume_management.resume_management_back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ResumeManagementBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResumeManagementBackApplication.class, args);
	}

}
