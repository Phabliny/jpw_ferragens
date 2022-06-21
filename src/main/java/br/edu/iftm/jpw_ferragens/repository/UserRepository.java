package br.edu.iftm.jpw_ferragens.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.iftm.jpw_ferragens.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByNome(String nome);
}
