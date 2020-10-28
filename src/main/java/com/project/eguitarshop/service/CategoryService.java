package com.project.eguitarshop.service;

import com.project.eguitarshop.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category findById(Long id);
    Category save(Category category);
    Category update(Long id,Category category);
    void deleteById(Long id);

}
