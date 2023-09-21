package com.example.testMavenProject2;

// import javax.websocket.server.PathParam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;


@SpringBootApplication 
@ImportResource({"classpath*:application-context.xml"})
public class TestMavenProject2Application {

	public static void main(String[] args) {
		SpringApplication.run(TestMavenProject2Application.class, args);
	}
}

// servlet outputSTream

//  inputStream -> baos -> servletoutputstream
// 