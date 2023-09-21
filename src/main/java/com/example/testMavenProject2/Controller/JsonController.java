package com.example.testMavenProject2.Controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.testMavenProject2.Entity.PersonnelEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class JsonController {

    @Autowired
    ObjectMapper objectMapper = new ObjectMapper();

    //TODO  :  /json -> new string[] = ["hello","world"] -> stringifier 
    @RequestMapping(value="/json", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String json(){
        List list = new ArrayList<String>();
        list.add("Hello");
        list.add("Word"); 
        try {
            String stringJson = objectMapper.writeValueAsString(list);
            return stringJson;
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
    }

    //TODO2 : /personnel/toJson -> Personnel perso -> stringify -> feed à crud/add
    @RequestMapping(value = "/personnel/toJson",method= RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String toJson(@ModelAttribute PersonnelEntity personnel){
        
        try {
            String stringJson = objectMapper.writeValueAsString(personnel);
            System.out.println("Etape 2" + stringJson);
            return stringJson;
        } catch (Exception e) {
            return "false";
        }
        
    }
}
