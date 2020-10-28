package com.project.eguitarshop.repository;

import com.project.eguitarshop.model.Guitar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuitarRepository extends JpaRepository<Guitar,Long> {
    List<Guitar> findAllByCategoryId(@Param("id") Long id);

}
