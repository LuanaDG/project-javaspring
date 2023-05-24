package com.luana.services;

import com.luana.entities.Pedido;
import com.luana.repositories.ClienteRepository;
import com.luana.repositories.PedidoRepository;
import com.luana.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;


    public Pedido findById(Integer id){
       Optional <Pedido> result = pedidoRepository.findById(id);
        return result.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + "," +
                        " Tipo: " + Pedido.class.getName()));
    }
}
