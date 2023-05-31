package com.luana.dto;

import com.luana.entities.Cliente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.io.Serializable;


public class ClienteDTO implements Serializable {

    private Integer id;
    @NotEmpty(message = "Preenchimento obrigatório")
    @Size(min=5, max=120, message="O tamanho deve ser entre 5 e 80 caracteres")
    private String nome;
    @NotEmpty(message = "Preenchimento obrigatório")
    @Email(message = "Email inválido")
    private String email;

    public ClienteDTO() {
    }

    public ClienteDTO(Cliente obj){
        id = obj.getId();
        nome = obj.getNome();
        email = obj.getEmail();
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
