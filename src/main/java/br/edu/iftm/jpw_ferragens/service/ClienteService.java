package br.edu.iftm.jpw_ferragens.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.edu.iftm.jpw_ferragens.domain.Cliente;
import br.edu.iftm.jpw_ferragens.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository repo;

    public List<Cliente> findAll() {
        return repo.findAll(Sort.by("id").ascending());
    }

    public Cliente create(Cliente novoCliente){
        return repo.save(novoCliente);
    }
}