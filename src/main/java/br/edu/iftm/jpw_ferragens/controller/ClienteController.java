package br.edu.iftm.jpw_ferragens.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iftm.jpw_ferragens.domain.Cliente;
import br.edu.iftm.jpw_ferragens.repository.ClienteRepository;

@CrossOrigin
@RestController
@RequestMapping("/jpw")
public class ClienteController {
    @Autowired
    ClienteRepository repo;
    @GetMapping("/cliente")
	public ResponseEntity<List<Cliente>> todos() {
		return ResponseEntity.status(HttpStatus.OK).body(repo.findAll());
	}

	@GetMapping("/cliente/{id}")
	public ResponseEntity<Cliente> getCliente(@PathVariable("id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(repo.findById(id).orElseThrow(RuntimeException::new));
	}

	@PostMapping("/cliente")
	public ResponseEntity<Cliente> novo(@Valid @RequestBody Cliente cliente) {
		return ResponseEntity.status(HttpStatus.OK).body(repo.save(cliente));
	}
}
