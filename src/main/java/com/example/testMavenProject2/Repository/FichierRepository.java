package com.example.testMavenProject2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.testMavenProject2.Entity.FichierEntity;

@Repository
public interface FichierRepository extends JpaRepository<FichierEntity, Long> {
    
}
