package com.project.eguitarshop.repository;

import com.project.eguitarshop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    boolean removeById(Long id);

}
