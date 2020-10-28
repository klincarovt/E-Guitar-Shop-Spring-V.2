package com.project.eguitarshop.web.RestController;

import com.project.eguitarshop.model.Category;
import com.project.eguitarshop.repository.CategoryRepository;
import com.project.eguitarshop.service.CategoryService;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryRestController {

    private final CategoryService categoryService;

    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> findAll(){
        return this.categoryService.findAll();
    }

    @GetMapping("/{id}")
    public Category findById(@PathVariable Long id){
        return this.categoryService.findById(id);
    }

    @PostMapping
    public Category save(@Valid @RequestBody Category category){
        return this.categoryService.save(category);
    }
    @PutMapping("/{id}")
    public Category update(@PathVariable Long id,
                           @Valid @RequestBody Category category){
        return this.categoryService.update(id,category);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        this.categoryService.deleteById(id);
    }
}
