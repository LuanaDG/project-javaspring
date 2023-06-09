package com.luana.resources;

import com.luana.entities.Pedido;
import com.luana.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {

    @Autowired
    private PedidoService clienteService;
   // @RequestMapping(value="/{id}", method = RequestMethod.GET)
    @GetMapping(value = "/{id}")
    public ResponseEntity<Pedido> find (@PathVariable Integer id){
        Pedido obj = clienteService.findById(id);
            return ResponseEntity.ok().body(obj);


    }
}
