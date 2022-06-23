package br.edu.iftm.jpw_ferragens.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.iftm.jpw_ferragens.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findAll();
}
