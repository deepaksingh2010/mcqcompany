package com.soartechnosoft.mcqcompany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan
@ComponentScan(basePackages = {"com.soartechnosoft.mcqcompany","com.soartechnosoft.mcqcompany.util","com.soartechnosoft.mcqcompany.repository.ExamTopicsRepository","com.soartechnosoft.mcqcompnay.models.maps"})
public class McqCompanyApplication {

	public static void main(String[] args) {
		SpringApplication.run(McqCompanyApplication.class, args);
	}

}
