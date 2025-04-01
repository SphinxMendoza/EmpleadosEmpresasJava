package com.mx.EmpleadosEmpresas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.EmpleadosEmpresas.dtos.Respuesta;
import com.mx.EmpleadosEmpresas.entidad.Empresa;
import com.mx.EmpleadosEmpresas.service.EmpresaService;

@RestController
@RequestMapping("empresas")
@CrossOrigin
public class EmpresaController {

	@Autowired
	EmpresaService service;

	@GetMapping("listar")
	public List<Empresa> listar() {
		return service.listar();
	}

	@PostMapping("guardar")
	public Respuesta guardar(@RequestBody Empresa empresa) {
		return service.guardar(empresa);
	}

	@PutMapping("editar")
	public Respuesta editar(@RequestBody Empresa empresa) {
		return service.editar(empresa);
	}

	@DeleteMapping("eliminar")
	public Respuesta eliminar(@RequestBody Empresa empresa) {
		return service.eliminar(empresa);
	}

	@GetMapping("buscar/{rfc}")
	public Respuesta buscar(@PathVariable("rfc") String rfc) {
		return service.buscar(rfc);
	}

}
