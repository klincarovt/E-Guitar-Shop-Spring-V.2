package com.project.eguitarshop.web.controller;

import com.project.eguitarshop.model.Category;
import com.project.eguitarshop.model.Craftsman;
import com.project.eguitarshop.model.Guitar;
import com.project.eguitarshop.service.CraftsmanService;
import com.project.eguitarshop.service.GuitarService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.ManyToMany;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/craftsmen")
public class CraftsmanController {

    private final CraftsmanService craftsmanService;
    private final GuitarService guitarService;

    public CraftsmanController(CraftsmanService craftsmanService, GuitarService guitarService) {
        this.craftsmanService = craftsmanService;
        this.guitarService = guitarService;
    }

    @GetMapping
    public String listCraftsmen(Model model){
        List<Craftsman> craftsmanList=this.craftsmanService.findAll();
        model.addAttribute("craftsmanList",craftsmanList);
        return "craftsmen";
    }

    @GetMapping("/add-new")
    public String addNewCraftsmanPage(Model model){
        List<Guitar> guitars = this.guitarService.findAll();
        model.addAttribute("guitars",guitars);
        model.addAttribute("craftsman",new Craftsman());
        return "add-craftsman";
    }

    @PostMapping
    public String saveOrUpdateCraftsman(Model model,
                                        @Valid Craftsman craftsman,
                                        BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<Guitar> guitars = this.guitarService.findAll();
            model.addAttribute("guitars",guitars);
            return "add-craftsman";
        }

        try {
            if(craftsman.getId()==null){
                this.craftsmanService.save(craftsman);
            }else {
                this.craftsmanService.update(craftsman.getId(),craftsman);
            }
            return "redirect:/craftsmen";
        }catch (RuntimeException e){
            return "redirect:/craftsmen/add-new?error="+e.getLocalizedMessage();
        }
    }

    @GetMapping("/{id}/edit")
    public String editCraftsmanPage(Model model, @PathVariable Long id) {
        try {
            Craftsman craftsman = this.craftsmanService.findById(id);
            List<Guitar> guitars=this.guitarService.findAll();
            model.addAttribute("craftsman", craftsman);
            model.addAttribute("guitars", guitars);
            return "add-craftsman";
        } catch (RuntimeException ex) {
            return "redirect:/craftsmen?error=" + ex.getMessage();
        }
    }




}
