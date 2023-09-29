package com.example.testMavenProject2;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;
import io.minio.errors.MinioException;


@SpringBootApplication
@ImportResource({"classpath*:application-context.xml"})
public class TestMavenProject2Application extends SpringBootServletInitializer{

	public static void main(String[] args) throws  MinioException{
		SpringApplication.run(TestMavenProject2Application.class, args);
	}
}