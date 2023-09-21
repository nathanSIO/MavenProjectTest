package com.example.testMavenProject2.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class testController {
    
    @GetMapping("/hello")
	public String hello(@RequestParam(value="name", defaultValue = "World") String name){
		return "index";
	}

	@RequestMapping(value = "/hello/{ajout}", method = RequestMethod.GET) 
	// @ResponseBody
	public String ajoutFlorian(@PathVariable String ajout){
		System.out.println("j'ajoute ça : " + ajout);
		return "index";
	}

	@RequestMapping(value = "/image", method = RequestMethod.GET)
	@ResponseBody
	public void getImage(HttpServletResponse response, byte[] imgData)throws IOException{
		InputStream in = Files.newInputStream(Paths.get("image/check.jpg"));
		// byte[] bytes = in.readAllBytes();
		response.setContentType(org.springframework.http.MediaType.IMAGE_JPEG_VALUE);
		// ServletOutputStream sos = response.getOutputStream();
		// sos.write(bytes);
		IOUtils.copy(in, response.getOutputStream());
	}	
}
