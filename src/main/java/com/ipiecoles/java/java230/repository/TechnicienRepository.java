package com.ipiecoles.java.java230.repository;

import com.ipiecoles.java.java230.model.Technicien;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TechnicienRepository extends PagingAndSortingRepository<Technicien, Long> {
    Technicien findByMatricule(String matricule);
    List<Technicien> findByGrade(Integer grade);
    List<Technicien> findByNomAndPrenom(String nom, String prenom);
}
