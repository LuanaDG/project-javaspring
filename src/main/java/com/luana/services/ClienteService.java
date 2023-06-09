package com.luana.services;

import com.luana.dto.CategoriaDTO;
import com.luana.dto.ClienteDTO;
import com.luana.dto.ClienteNewDTO;
import com.luana.entities.Categoria;
import com.luana.entities.Cidade;
import com.luana.entities.Cliente;
import com.luana.entities.Endereco;
import com.luana.entities.enums.TipoCliente;
import com.luana.repositories.CategoriaRepository;
import com.luana.repositories.CidadeRepository;
import com.luana.repositories.ClienteRepository;
import com.luana.repositories.EnderecoRepository;
import com.luana.services.exceptions.DataIntegrityException;
import com.luana.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;


    @Autowired
    private EnderecoRepository enderecoRepository;
    public Cliente findById(Integer id){
       Optional <Cliente> result = clienteRepository.findById(id);
        return result.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + "," +
                        " Tipo: " + Cliente.class.getName()));
    }
    @Transactional
    public Cliente insert(Cliente obj){
        obj.setId(null);
        obj = clienteRepository.save(obj);
        enderecoRepository.saveAll(obj.getEnderecos());
        return obj;
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
            throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionados!");
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
    public Cliente fromDTO(ClienteNewDTO objDto){
        Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()));
        Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
        Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
        cli.getEnderecos().add(end);
        cli.getTelefones().add(objDto.getTelefone1());
        if (objDto.getTelefone2()!=null) {
            cli.getTelefones().add(objDto.getTelefone2());
        }
        if (objDto.getTelefone3()!=null) {
            cli.getTelefones().add(objDto.getTelefone3());
        }
        return cli;
    }
    private void updateData(Cliente newObj, Cliente obj){
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }
}
