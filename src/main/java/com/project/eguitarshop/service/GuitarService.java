package com.project.eguitarshop.service;

import com.project.eguitarshop.model.Guitar;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface GuitarService {

    List<Guitar> FindAllByCategoryId(Long categoryId);
    List<Guitar> findAll();
    Guitar findById(Long id);
    Guitar save(Guitar guitar, MultipartFile image) throws IOException;
    Guitar update(Long id, Guitar guitar, MultipartFile image) throws IOException;
    void deleteById(Long id);

}
