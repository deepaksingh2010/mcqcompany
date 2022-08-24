package com.soartechnosoft.mcqcompany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

import java.nio.file.Path;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import com.soartechnosoft.mcqcompany.config.DataStaxAstraProperties;

@SpringBootApplication
@EntityScan
@EnableConfigurationProperties(DataStaxAstraProperties.class)
@ComponentScan(basePackages = {"com.soartechnosoft.mcqcompany","com.soartechnosoft.mcqcompany.util","com.soartechnosoft.mcqcompany.repository.ExamTopicsRepository","com.soartechnosoft.mcqcompnay.models.maps","com.soartechnosoft.mcqcompany.config"})
public class McqCompanyApplication {

	public static void main(String[] args) {
		SpringApplication.run(McqCompanyApplication.class, args);
	}
	@Bean
    public CqlSessionBuilderCustomizer sessionBuilderCustomizer(DataStaxAstraProperties astraProperties) {
        Path bundle = astraProperties.getSecureConnectBundle().toPath();
        return builder -> builder.withCloudSecureConnectBundle(bundle);
    }
}
