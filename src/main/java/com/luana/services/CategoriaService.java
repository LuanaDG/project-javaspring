package com.luana.services;

import com.luana.entities.Categoria;
import com.luana.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;


    public Categoria findById(Integer id){
       Optional <Categoria> result = categoriaRepository.findById(id);
        return result.orElse(null);
    }
}