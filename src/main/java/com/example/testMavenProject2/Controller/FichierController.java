package com.example.testMavenProject2.Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.testMavenProject2.Service.UploadService;


@Controller
// @Component
@RequestMapping(value="/fichier", method = RequestMethod.GET)
public class FichierController {

    @Autowired
    private UploadService uploadService;

    @RequestMapping(value = "/accueil", method = RequestMethod.GET)
    public String Acceuil(){
        return "uploadFichier";
    }

    // @RequestMapping(value="/upload", method = RequestMethod.POST)
    // public void uploadFile(@RequestParam MultipartFile file) throws IOException{
        
    //     System.err.println("je suis ici : " + file);
    //     String fileName  = file.getOriginalFilename();
    //     int filelenth = file.getBytes().length;
    //     System.err.println(fileName);
    //     System.err.println(filelenth);    
    // }

    @RequestMapping(value="/upload", method = RequestMethod.POST)
    public String uploadFile(@RequestParam MultipartFile file) throws Exception {
        System.err.println("je suis ici : " + file);

        String fileName  = file.getOriginalFilename();
        int filelength = file.getBytes().length;

        System.err.println(fileName);
        System.err.println(filelength);

        // uploadService.uploadFile(null, 0);:
        uploadService.uploadFile(fileName, filelength);
        Map<String, String> result = new HashMap<>();
        result.put("key", file.getOriginalFilename());
        return "erreur";
    }
}
