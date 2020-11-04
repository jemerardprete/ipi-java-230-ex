package com.ipiecoles.java.java230.repository;

import com.ipiecoles.java.java230.model.Employe;
import org.joda.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

// 2.1 : Créer l'interface EmployeRepository dans le package repository et la faire implémenter CrudRepository
// 3.2.1 : Modifier EmployeRepository pour lui faire implémenter PagingAndSortingRepository
public interface EmployeRepository extends PagingAndSortingRepository<Employe,Long> {
    // 3.1 : Déclarer les méthodes permettant d'effectuer les recherches suivantes
    // 3.1.1 : Recherche d'employés par matricule : SELECT * FROM employe WHERE matricule = ?;
    Employe findByMatricule(String matricule);

    // 3.1.2 : Recherche d'employés par nom et prénom : SELECT * FROM employe WHERE nom = ? AND prenom = ?;
    List<Employe> findByNomAndPrenom(String nom, String prenom);

    // 3.1.3 : Recherche d'employés par nom sans prendre en compte la casse : SELECT * FROM employe WHERE lower(nom) = ?;
    List<Employe> findByNomIgnoreCase(String nom);

    // 3.1.4 : Recherche d'employés embauchés avant une certaine date : SELECT * FROM employe WHERE dateEmbauche < ?;
    List<Employe> findByDateEmbaucheBefore(LocalDate date);

    // 3.1.5 : Recherche d'employés embauchés après une certaine date : SELECT * FROM employe WHERE dateEmbauche > ?;
    List<Employe> findByDateEmbaucheAfter(LocalDate date);

    // 3.1.6 : Recherche d'employés gagnant plus de X euros et ordonnés selon leur salaire : SELECT * FROM employe WHERE salaire > ? ORDER BY salaire DESC;
    List<Employe> findBySalaireGreaterThanOrderBySalaireDesc(Double salaire);

    // 3.2.2 : Ajouter une méthode permettant de rechercher les employés en fonction de leur nom, sans prendre en compte la casse, et ce de manière paginée.
    Page<Employe> findByNomIgnoreCase(String nom, Pageable pageable);

    // 3.3 : Ajouter une méthode findByNomOrPrenomAllIgnoreCase prenant en parametre un String nomOuPrenom
    // et qui recherche sans prendre en compte la casse les employés ayant ce paramètre en nom ou en prénom
    @Query("select e from Employe e where lower(e.prenom) = lower(:nomOuPrenom) or lower(e.nom) = lower(:nomOuPrenom)")
    List<Employe> findByNomOrPrenomAllIgnoreCase(@Param("nomOuPrenom") String nomOuPrenom);

    // 3.4 : Ajouter une méthode findEmployePlusRiches qui récupère les employés dont le salaire est supérieur au salaire moyen des employés
    @Query(value = "SELECT * FROM Employe WHERE salaire > (SELECT avg(e2.salaire) FROM Employe e2)", nativeQuery = true)
    List<Employe> findEmployePlusRiches();

}
