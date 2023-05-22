package com.luana.services;

import com.luana.entities.Categoria;
import com.luana.repositories.CategoriaRepository;
import com.luana.services.exceptions.ObjectNotFoundException;
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
        return result.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + "," +
                        " Tipo: " + Categoria.class.getName()));
    }
}
