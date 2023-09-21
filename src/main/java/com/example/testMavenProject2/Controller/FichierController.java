package com.example.testMavenProject2.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;

import org.apache.commons.compress.utils.FileNameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jlefebure.spring.boot.minio.MinioService;

import io.minio.MinioClient;

@RequestMapping(value="/fichier")
@Controller
public class FichierController {

    @Autowired
    private MinioService minioService;

    
    

    @RequestMapping(value = "/accueil", method = RequestMethod.GET)
    public String Acceuil(){
        return "uploadFichier";
    }

    @RequestMapping(value="/upload", method = RequestMethod.POST)
    public void uploadFile(@RequestParam MultipartFile file) throws IOException{
        
        System.err.println("je suis ici : " + file);
        String fileName  = file.getOriginalFilename();
        int filelenth = file.getBytes().length;
        System.err.println(fileName);
        System.err.println(filelenth);


        // Path path = Path.of(file.getOriginalFilename());
        // try {
        //     minioService.upload(path, file.getInputStream(), file.getContentType());
        // } catch (MinioExeption e) {
        //     // TODO: handle exception
        //     throw new IllegalStateException("The file cannot be upload on the internal storage. Please retry later", e);
        // }
        // catch (IOException e){

        // }

        
    }
    // public ResponseEntity<?> handleFileUpload( @PathVariable MultipartFile file ) {
    //     System.err.println("je suis ici : " + file);
    //     String fileName = file.getOriginalFilename();
    //     try {
    //     file.transferTo( new File("C:\\upload\\" + fileName));
    //     } catch (Exception e) {
    //     return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    //     } 
    //     return ResponseEntity.ok("File uploaded successfully.");
    // }
}
