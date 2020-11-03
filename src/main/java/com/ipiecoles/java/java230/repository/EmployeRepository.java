package com.ipiecoles.java.java230.repository;

import com.ipiecoles.java.java230.model.Employe;
import org.springframework.data.repository.CrudRepository;

// 2.1 : Créer l'interface EmployeRepository dans le package repository et la faire implémenter CrudRepository
public interface EmployeRepository extends CrudRepository<Employe,Long> {
    // 3.1 : Déclarer les méthodes permettant d'effectuer les recherches suivantes
    // 3.1.1 : Recherche d'employés par matricule


}
