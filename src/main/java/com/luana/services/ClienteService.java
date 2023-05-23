package com.luana.services;

import com.luana.entities.Cliente;
import com.luana.repositories.CategoriaRepository;
import com.luana.repositories.ClienteRepository;
import com.luana.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;


    public Cliente findById(Integer id){
       Optional <Cliente> result = clienteRepository.findById(id);
        return result.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + "," +
                        " Tipo: " + Cliente.class.getName()));
    }
}
