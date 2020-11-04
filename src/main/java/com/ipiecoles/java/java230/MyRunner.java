package com.ipiecoles.java.java230;

import com.ipiecoles.java.java230.model.Employe;
import com.ipiecoles.java.java230.model.Manager;
import com.ipiecoles.java.java230.model.Technicien;
import com.ipiecoles.java.java230.repository.EmployeRepository;
import com.ipiecoles.java.java230.repository.ManagerRepository;
import com.ipiecoles.java.java230.repository.TechnicienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Component
public class MyRunner implements CommandLineRunner {

    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private TechnicienRepository technicienRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public void run(String... strings) throws Exception {

        // Pour le 3.1.1

        System.out.println("Employé ayant le matricule C0004 : ");

        Employe e = employeRepository.findByMatricule("C00004");
        if(e != null){
            System.out.println(e.toString());
        } else {
            System.out.println("Employé non trouvé");
        }

        // Pour le 3.2

        System.out.println("Recherche d'un employé en fonction de son nom");

        PageRequest pageRequest = PageRequest.of(0, 5, Sort.Direction.ASC, "prenom");
        Page<Employe> employeList = employeRepository.findByNomIgnoreCase("Andre", pageRequest);
        System.out.println("Nombre de résultats total : " + employeList.getTotalElements());
        System.out.println("Nombre de pages total : " + employeList.getTotalPages());
        System.out.println("Taille de la page : " + employeList.getSize());
        System.out.println("Trié par : " + employeList.getSort().toString());

        for(Employe employe : employeList){
            System.out.println(employe.toString());
        }

        // Pour le 3.3

        System.out.println("Recherche d'un employé en fonction de son nom ou prénom");

        List<Employe> employeList2 = employeRepository.findByNomOrPrenomAllIgnoreCase("JULIEN");

        for(Employe employe : employeList2){
            // System.out.println(employe.toString());
            System.out.println(employe.getPrimeAnnuelle());
        }

        // JSP

        Iterable<Technicien> technicienIterable = technicienRepository.findAll();
        for (Technicien employe : technicienIterable){
            System.out.println(employe.toString());
            System.out.println(employe.getPrimeAnnuelle());
            System.out.println(employe.getManager().getNom());
        }

        Manager manager = managerRepository.findOneWithEquipeById(44L);
        for(Technicien technicien : manager.getEquipe()){
            System.out.println(technicien.toString());
        }

        /* System.out.println("Bonjour");
        Long nbEmployes = employeRepository.count();
        System.out.println("Il y a " + nbEmployes + " dans la base de données");
        PageRequest pageRequest = PageRequest.of(1,10, Sort.Direction.ASC, "nom", "prenom");
        Sort s = Sort.by(Sort.Direction.ASC, "nom", "prenom");
        Optional<Employe> employe = employeRepository.findById(55L);
        if(employe.isEmpty()){
            System.out.println("Employe inexistant");
        } else {
            Employe e = employe.get();
            e.setSalaire(e.getSalaire() + 200);
            e = employeRepository.save(e);
            System.out.println(e.toString());
        }
        for(Employe emp : employeRepository.findAll()){
            System.out.println(emp.toString());
        } */
    }

    public static void print(Object t) {
        System.out.println(t);
    }
}
