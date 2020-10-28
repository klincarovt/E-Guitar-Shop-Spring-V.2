package com.project.eguitarshop.web.RestController;


import com.project.eguitarshop.model.Guitar;
import com.project.eguitarshop.service.GuitarService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api/guitars")
public class GuitarRestController {

    private final GuitarService guitarService;

    public GuitarRestController(GuitarService guitarService) {
        this.guitarService = guitarService;
    }

    @GetMapping
    @Secured("ROLE_ADMIN")
    public List<Guitar> findAll(){
        return this.guitarService.findAll();
    }

    @GetMapping("/{id}")
    public Guitar findById(@PathVariable Long id){
        return this.guitarService.findById(id);
    }

    @GetMapping("/by-category/{categoryId}")
    public List<Guitar> findByCategoryId(@RequestParam Long id){
        return this.guitarService.FindAllByCategoryId(id);
    }

    @GetMapping("/by-price")
    public List<Guitar> findAllByPrice(@RequestParam Integer price){
        List<Guitar> guitars=guitarService.findAll();
        List<Guitar> guitarsByPrice = new LinkedList<>();
        for (Guitar g : guitars) {
            if(g.getPrice()==price){
                guitarsByPrice.add(g);
            }

        }
        return guitarsByPrice;
    }

    @PostMapping
    public Guitar save(@Valid Guitar guitar, @RequestParam(required = false)MultipartFile image) throws IOException{
        return this.guitarService.save(guitar,image);
    }

    @PutMapping("/{id}")
    public Guitar update(@PathVariable Long id,
                         @Valid Guitar guitar,
                         @RequestParam(required=false)MultipartFile image)
    throws IOException
    {
        return this.guitarService.update(id,guitar,image);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        this.guitarService.deleteById(id);
    }

}
