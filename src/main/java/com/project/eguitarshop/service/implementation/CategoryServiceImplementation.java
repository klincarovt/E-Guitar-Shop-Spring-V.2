package com.project.eguitarshop.service.implementation;

import com.project.eguitarshop.model.Category;
import com.project.eguitarshop.model.exceptions.CategoryNotFoudnException;
import com.project.eguitarshop.repository.CategoryRepository;
import com.project.eguitarshop.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImplementation implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImplementation(CategoryRepository categoryRepository)
    {
        this.categoryRepository=categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return this.categoryRepository.findById(id).orElseThrow(
                () -> new CategoryNotFoudnException(id)
        );
    }

    @Override
    public Category save(Category category) {
        return this.categoryRepository.save(category);
    }

    @Override
    public Category update(Long id, Category category) {
        Category updCategory=this.findById(id);

        updCategory.setName(category.getName());
        updCategory.setDescription(category.getDescription());

        return this.categoryRepository.save(updCategory);
    }

    @Override
    public void deleteById(Long id) {
        this.categoryRepository.deleteById(id);
    }

}
