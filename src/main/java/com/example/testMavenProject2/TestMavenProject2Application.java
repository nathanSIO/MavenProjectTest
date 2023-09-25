package com.example.testMavenProject2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;


@SpringBootApplication
@ImportResource({"classpath*:application-context.xml"})
// @ComponentScan({"io.minio.*"})
// @EntityScan("io.minio.domain")
// @EnableJpaRepositories("io.minio.repository")
// @ComponentScan(basePackages = "io.minio.MinioClient")
// @PropertySource("classpath:s3.properties")
public class TestMavenProject2Application extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(TestMavenProject2Application.class, args);
	}
}

// servlet outputSTream

//  inputStream -> baos -> servletoutputstream
// io.minio.MinioClient

// (scanBasePackages = {"Controller", "Service", "Entity", "Repository","org.springframework.boot.spring-boot-starter-thymeleaf"})