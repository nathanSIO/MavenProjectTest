package com.example.testMavenProject2.Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.testMavenProject2.Entity.FichierEntity;
import com.example.testMavenProject2.Repository.FichierRepository;
import com.example.testMavenProject2.Service.UploadService;


@Controller
// @Component
@RequestMapping(value="/fichier", method = RequestMethod.GET)
public class FichierController {

    @Autowired
    private UploadService uploadService;

    @Autowired
    private FichierRepository fichierRepository;

    @RequestMapping(value = "/accueil", method = RequestMethod.GET)
    public String Acceuil(){
        return "uploadFichier";
    }

    @RequestMapping(value="/upload", method = RequestMethod.POST)
    public String uploadFile(@RequestParam MultipartFile file) throws Exception {
        System.err.println("je suis ici : " + file);

        String uuid  = UUID.randomUUID().toString();
        int filelength = file.getBytes().length;

        System.err.println(uuid);
        System.err.println(filelength);

        FichierEntity f1 = new FichierEntity(null, UUID.randomUUID().toString(), file.getOriginalFilename());
        uploadService.uploadFile(uuid, file.getOriginalFilename());
        // Map<String, String> result = new HashMap<>();
        // result.put(uuid, file.getOriginalFilename());
        fichierRepository.save(f1);

        uploadService.getFile(uuid);
        return "erreur";
    }
}
