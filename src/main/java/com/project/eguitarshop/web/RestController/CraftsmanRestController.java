package com.project.eguitarshop.web.RestController;

import com.project.eguitarshop.model.Craftsman;
import com.project.eguitarshop.service.CraftsmanService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/craftsmen")
public class CraftsmanRestController {

    private final CraftsmanService craftsmanService;

    public CraftsmanRestController(CraftsmanService craftsmanService) {
        this.craftsmanService = craftsmanService;
    }

    @GetMapping
    public List<Craftsman> findAll(){
        return this.craftsmanService.findAll();
    }

    @GetMapping("/{id}")
    public Craftsman findById(@PathVariable Long id){
        return this.craftsmanService.findById(id);
    }

    @PostMapping
    public Craftsman save(@Valid @RequestBody Craftsman craftsman){
        return this.craftsmanService.save(craftsman);
    }

    @PutMapping("/{id}")
    public Craftsman update(@PathVariable Long id,
                            @Valid @RequestBody Craftsman craftsman){
        return this.craftsmanService.update(id,craftsman);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        this.craftsmanService.deleteById(id);
    }

}
