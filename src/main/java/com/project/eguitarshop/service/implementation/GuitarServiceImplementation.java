package com.project.eguitarshop.service.implementation;

import com.project.eguitarshop.model.Category;
import com.project.eguitarshop.model.Guitar;
import com.project.eguitarshop.model.exceptions.GuitarNotFoundException;
import com.project.eguitarshop.repository.CategoryRepository;
import com.project.eguitarshop.repository.GuitarRepository;
import com.project.eguitarshop.service.CategoryService;
import com.project.eguitarshop.service.GuitarService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;


@Service
public class GuitarServiceImplementation implements GuitarService {

    private final GuitarRepository guitarRepository;
    private final CategoryService categoryService;

    public GuitarServiceImplementation(GuitarRepository guitarRepository,CategoryService categoryService){
        this.guitarRepository=guitarRepository;
        this.categoryService=categoryService;
    }
    @Override
    public List<Guitar> FindAllByCategoryId(Long categoryId) {
        return guitarRepository.findAllByCategoryId(categoryId);

    }


    @Override
    public List<Guitar> findAll() {
        return this.guitarRepository.findAll();
    }

    @Override
    public Guitar findById(Long id) {
        return guitarRepository.findById(id).orElseThrow(
                () -> new GuitarNotFoundException(id)
        );
    }

    @Override
    public Guitar save(Guitar guitar, MultipartFile image) throws IOException {
        Category category = this.categoryService.findById(guitar.getCategory().getId());
        guitar.setCategory(category);

        if (image != null && !image.getName().isEmpty()) {
            byte[] bytes = image.getBytes();
            String base64Image = String.format("data:%s;base64,%s", image.getContentType(), Base64.getEncoder().encodeToString(bytes));
            guitar.setBase64(base64Image);
        }
        return this.guitarRepository.save(guitar);
    }

    @Override
    public Guitar update(Long id, Guitar guitar, MultipartFile image) throws IOException {
        Guitar updGuitar=this.findById(id);
        Category category = this.categoryService.findById(guitar.getCategory().getId());

        updGuitar.setName(guitar.getName());
        updGuitar.setSamples(guitar.getSamples());
        updGuitar.setCategory(category);
        updGuitar.setBase64(guitar.getBase64());

        if (image != null && !image.getName().isEmpty()) {
            byte[] bytes = image.getBytes();
            String base64Image = String.format("data:%s;base64,%s", image.getContentType(), Base64.getEncoder().encodeToString(bytes));
            guitar.setBase64(base64Image);
        }

        return guitarRepository.save(updGuitar);


    }

    @Override
    public void deleteById(Long id) {
        this.guitarRepository.deleteById(id);
    }
}
