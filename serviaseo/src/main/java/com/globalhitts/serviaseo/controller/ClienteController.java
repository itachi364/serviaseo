package com.globalhitts.serviaseo.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globalhitts.serviaseo.entity.Cliente;
import com.globalhitts.serviaseo.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	@Autowired
	private ClienteService clienteService;

	@PostMapping("/registrar")
    public ResponseEntity<Cliente> registrarCliente(@RequestBody Cliente cliente) {
		Cliente nuevoCliente = clienteService.registrarCliente(cliente);
		return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
	}

	@GetMapping("/verificar/{tipoDocumento}/{numeroDocumento}")
    public ResponseEntity<String> verificarCliente(@PathVariable int tipoDocumento, @PathVariable int numeroDocumento) {
		Optional<Cliente> cliente = clienteService.verificarCliente(tipoDocumento, numeroDocumento);
		return cliente.isPresent() ? new ResponseEntity<>("Cliente existe", HttpStatus.OK)
				: new ResponseEntity<>("Cliente no existe", HttpStatus.NOT_FOUND);
	}
	@GetMapping("/buscar/{tipoDocumento}/{numeroDocumento}")
    public ResponseEntity<String> findClienteByTipoDocumentoAndNumeroDocumento(@PathVariable int tipoDocumento, @PathVariable int numeroDocumento) {
		Optional<Cliente> cliente = clienteService.findClienteByTipoDocumentoAndNumeroDocumento(tipoDocumento, numeroDocumento);
		return cliente.isPresent() ? new ResponseEntity<>(cliente.toString(), HttpStatus.OK)
				: new ResponseEntity<>("Cliente no existe", HttpStatus.NOT_FOUND);
	}
}
