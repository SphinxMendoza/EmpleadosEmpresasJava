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
import com.mx.EmpleadosEmpresas.entidad.Empleado;
import com.mx.EmpleadosEmpresas.service.EmpleadosService;

@RestController
@RequestMapping("empleados")
@CrossOrigin
public class EmpleadoController {

	@Autowired
	EmpleadosService service;
	
	@GetMapping("listar")
	public List<Empleado> listar() {
		return service.listar();
	}

	@PostMapping("guardar")
	public Respuesta guardar(@RequestBody Empleado empresa) {
		return service.guardar(empresa);
	}

	@PutMapping("editar")
	public Respuesta editar(@RequestBody Empleado empresa) {
		return service.editar(empresa);
	}

	@DeleteMapping("eliminar")
	public Respuesta eliminar(@RequestBody Empleado empresa) {
		return service.eliminar(empresa);
	}

	@GetMapping("buscar/{curp}")
	public Respuesta buscar(@PathVariable("curp") String curp) {
		return service.buscar(curp);
	}
}
