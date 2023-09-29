package com.example.testMavenProject2.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.testMavenProject2.Entity.PersonnelEntity;

@Repository
public interface PersonnelRepository extends JpaRepository<PersonnelEntity, Long>{

    @Query("SELECT p FROM PersonnelEntity p WHERE id=?1 ")
    Optional<PersonnelEntity> findById(Long id);

}
