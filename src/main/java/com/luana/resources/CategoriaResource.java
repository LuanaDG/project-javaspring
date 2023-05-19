package com.luana.resources;

import com.luana.entities.Categoria;
import com.luana.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;
   // @RequestMapping(value="/{id}", method = RequestMethod.GET)
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> find (@PathVariable Integer id){
        Categoria obj = categoriaService.findById(id);
            return ResponseEntity.ok().body(obj);


    }
}
