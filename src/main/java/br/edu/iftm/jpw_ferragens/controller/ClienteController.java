package br.edu.iftm.jpw_ferragens.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
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

import br.edu.iftm.excel.ClienteExcelExporter;
import br.edu.iftm.jpw_ferragens.domain.Cliente;
import br.edu.iftm.jpw_ferragens.service.ClienteService;

@CrossOrigin
@RestController
@RequestMapping("/jpw")
public class ClienteController {
    @Autowired
	ClienteService service;
	
	@GetMapping("/clientes")
    public ResponseEntity<List<Cliente>> getAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

	@PostMapping("/cliente")
	public ResponseEntity<Cliente> novo(@Valid @RequestBody Cliente cliente) {
		return ResponseEntity.status(HttpStatus.OK).body(service.create(cliente));
	}

	@GetMapping("/clientes/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=clientes_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

		List<Cliente> listClients = service.findAll();

        ClienteExcelExporter excelExporter = new ClienteExcelExporter(listClients);

        excelExporter.export(response);
    }
}