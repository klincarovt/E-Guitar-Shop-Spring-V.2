package com.project.eguitarshop.web.controller;


import com.project.eguitarshop.model.Category;
import com.project.eguitarshop.model.Craftsman;
import com.project.eguitarshop.model.Guitar;
import com.project.eguitarshop.service.CategoryService;
import com.project.eguitarshop.service.GuitarService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;


    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String listCategories(Model model){
        List<Category> categoryList=this.categoryService.findAll();
        model.addAttribute("categoryList",categoryList);
        return "categories";
    }
    @GetMapping("/add-new")
    public String addNewCategoryPage(Model model){
        model.addAttribute("category",new Category());
        return "add-category";
    }

   @PostMapping
    public String saveOrUpdateCategory(@Valid Category category,
                                        BindingResult bindingResult){
       if(bindingResult.hasErrors()){
           return "add-category";
       }
       try {
           if(category.getId()==null){
               this.categoryService.save(category);
           }else {
               this.categoryService.update(category.getId(),category);
           }
           return "redirect:/categories";
       }catch (RuntimeException e){
           return "redirect:/categories/add-new?error="+e.getLocalizedMessage();
       }
    }

    @GetMapping("/{id}/edit")
    public String editCategoryPage(Model model, @PathVariable Long id) {
        try {
            Category category = this.categoryService.findById(id);
            model.addAttribute("category", category);
            return "add-category";
        } catch (RuntimeException ex) {
            return "redirect:/categories?error=" + ex.getMessage();
        }
    }



}
