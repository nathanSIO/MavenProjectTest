package com.example.testMavenProject2.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.testMavenProject2.Entity.PersonnelEntity;
import com.example.testMavenProject2.Repository.PersonnelRepository;


public class PersonnelServiceImpl implements PersonnelService{

    @Autowired
    private PersonnelRepository personnelRepository;

    // // Create Personnel
    // @Override
    // public PersonnelEntity createPersonnelEntity(PersonnelEntity personnel){
    //     return personnelRepository.save(personnel);
    // }
    
    // // Read/GET all User 
    // public Iterable<PersonnelEntity> listAllPersonnel(){
    //     return personnelRepository.findAll();
    // }

    // // Update Personnel 
    // public PersonnelEntity getPersonnelById(Long id){
    //     return personnelRepository.findById(id).get();
    // }

    // // Delete Personnel
    // public void deletePersonnel(Long id){
    //     personnelRepository.deleteById(id);
    
    
    
}
