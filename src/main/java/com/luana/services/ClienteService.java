package com.luana.services;

import com.luana.dto.CategoriaDTO;
import com.luana.dto.ClienteDTO;
import com.luana.entities.Categoria;
import com.luana.entities.Cliente;
import com.luana.repositories.CategoriaRepository;
import com.luana.repositories.ClienteRepository;
import com.luana.services.exceptions.DataIntegrityException;
import com.luana.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public Cliente update(Cliente obj){
        Cliente newObj = findById(obj.getId());
        updateData(newObj, obj);
        return clienteRepository.save(newObj);
    }
    public void delete(Integer id){
        findById(id);
        try {
            clienteRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos!");
        }
    }
    public List<Cliente> findAll(){

        return clienteRepository.findAll();
    }
    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return clienteRepository.findAll(pageRequest);
    }
    public Cliente fromDTO(ClienteDTO objDto){
        return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
    }
    private void updateData(Cliente newObj, Cliente obj){
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }
}
