package com.project.eguitarshop.service;

import com.project.eguitarshop.model.Craftsman;

import java.util.List;

public interface CraftsmanService {
    List<Craftsman> findAll();
    Craftsman findById(Long id);
    Craftsman save(Craftsman craftsman);
    Craftsman update(Long id,Craftsman craftsman);
    void deleteById(Long id);

}
