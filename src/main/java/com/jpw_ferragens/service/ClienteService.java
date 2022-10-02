package com.jpw_ferragens.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jpw_ferragens.domain.Cliente;
import com.jpw_ferragens.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    public List<Cliente> findAll() {
        return repo.findAll(Sort.by("id").ascending());
    }

    public Cliente create(Cliente novoCliente){
        return repo.save(novoCliente);
    }

    public void delete(Long id){
        Optional<Cliente> cliente = repo.findById(id);
        if(!cliente.isPresent()) {
            throw new RuntimeException("Error client not found!");
        }
        repo.deleteById(id);
    }
}