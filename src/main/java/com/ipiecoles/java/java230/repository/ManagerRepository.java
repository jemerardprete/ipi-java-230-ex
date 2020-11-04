package com.ipiecoles.java.java230.repository;

import com.ipiecoles.java.java230.model.Manager;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface ManagerRepository extends PagingAndSortingRepository<Manager, Long> {

    @EntityGraph(attributePaths = "equipe")
    @Query("from Manager where id = :id")
    Manager findOneWithEquipeById(@Param("id") Long id);
}
