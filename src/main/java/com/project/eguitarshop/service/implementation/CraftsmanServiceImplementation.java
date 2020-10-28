package com.project.eguitarshop.service.implementation;

import com.project.eguitarshop.model.Category;
import com.project.eguitarshop.model.Craftsman;
import com.project.eguitarshop.model.exceptions.CraftsmanNotFoundException;
import com.project.eguitarshop.repository.CraftsmanRepository;
import com.project.eguitarshop.service.CraftsmanService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CraftsmanServiceImplementation  implements CraftsmanService {
    private final CraftsmanRepository craftsmanRepository;

    public CraftsmanServiceImplementation(CraftsmanRepository craftsmanRepository) {
        this.craftsmanRepository = craftsmanRepository;
    }

    @Override
    public List<Craftsman> findAll() {
        return this.craftsmanRepository.findAll();
    }

    @Override
    public Craftsman findById(Long id) {
        return this.craftsmanRepository.findById(id).orElseThrow(
                () -> new CraftsmanNotFoundException(id)
        );
    }

    @Override
    public Craftsman save(Craftsman craftsman) {
        return this.craftsmanRepository.save(craftsman);
    }

    @Override
    public Craftsman update(Long id, Craftsman craftsman) {
        Craftsman updCraftsman = this.findById(id);

        updCraftsman.setName(craftsman.getName());
        updCraftsman.setGuitars(craftsman.getGuitars());

        return this.craftsmanRepository.save(updCraftsman);
    }

    @Override
    public void deleteById(Long id) {
        this.craftsmanRepository.removeById(id);
    }
}
