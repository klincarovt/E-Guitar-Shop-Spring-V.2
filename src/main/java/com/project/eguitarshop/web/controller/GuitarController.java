package com.project.eguitarshop.web.controller;

import com.project.eguitarshop.model.Category;
import com.project.eguitarshop.model.Craftsman;
import com.project.eguitarshop.model.Guitar;
import com.project.eguitarshop.service.CategoryService;
import com.project.eguitarshop.service.CraftsmanService;
import com.project.eguitarshop.service.GuitarService;
import org.dom4j.rule.Mode;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/guitars")
public class GuitarController {

    private final GuitarService guitarService;
    private final CategoryService categoryService;
    private final CraftsmanService craftsmanService;

    public GuitarController(GuitarService guitarService, CategoryService categoryService, CraftsmanService craftsmanService) {
        this.guitarService = guitarService;
        this.categoryService = categoryService;
        this.craftsmanService = craftsmanService;
    }

    @GetMapping
    public String listGuitars(Model model){
        List<Guitar> guitarList=this.guitarService.findAll();
        model.addAttribute("guitars",guitarList);
        return "guitars";
    }

    @PostMapping
    public String saveOrUpdateGuitar(
            Model model,
            @Valid Guitar guitar,
            BindingResult bindingResult,
            @RequestParam MultipartFile image
            ) throws IOException{

        if(bindingResult.hasErrors()){
            List<Category> categories=this.categoryService.findAll();
            model.addAttribute("categories",categories);
            return "add-guitar";
        }

        try {
            if(guitar.getId()==null){
                this.guitarService.save(guitar,image);
            }else {
                this.guitarService.update(guitar.getId(),guitar,image);
            }
            return "redirect:/guitars";
        }catch (RuntimeException e){
            return "redirect:/guitars/add-new?error="+e.getLocalizedMessage();
        }
    }

    @GetMapping("/add-new")
    public String addNewGuitarPage(Model model){
        List<Category> categories=this.categoryService.findAll();
        List<Craftsman> craftsmen=this.craftsmanService.findAll();
        model.addAttribute("craftsmen",craftsmen);
        model.addAttribute("categories",categories);
        model.addAttribute("guitar",new Guitar());
        return "add-guitar";
    }

    @GetMapping("{id}/edit")
    public String editGuitarPage(@PathVariable Long id,Model model){
        try {
            Guitar guitar=this.guitarService.findById(id);
            List<Category> categories=this.categoryService.findAll();
            model.addAttribute("guitar",guitar);
            model.addAttribute("categories",categories);
            return "add-guitar";
        }catch (RuntimeException e){
            return "redirect:/guitars?error="+e.getLocalizedMessage();
        }
    }

    @DeleteMapping("/{id}/delete")
    public String deleteGuitarWithDelete(@PathVariable Long id){
        this.guitarService.deleteById(id);
        return "redirect:/guitars";
    }

    @PostMapping("/{id}/delete")
    public String deleteGuitarWithPost(@PathVariable Long id){
        this.guitarService.deleteById(id);
        return "redirect:/guitars";
    }
}
