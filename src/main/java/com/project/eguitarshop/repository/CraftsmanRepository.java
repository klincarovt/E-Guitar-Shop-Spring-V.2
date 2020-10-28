package com.project.eguitarshop.repository;

import com.project.eguitarshop.model.Craftsman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CraftsmanRepository extends JpaRepository<Craftsman,Long> {
    boolean removeById(Long id);
}
