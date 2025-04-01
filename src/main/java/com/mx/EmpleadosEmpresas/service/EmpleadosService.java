package com.mx.EmpleadosEmpresas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.EmpleadosEmpresas.dao.EmpleadoDao;
import com.mx.EmpleadosEmpresas.dtos.Respuesta;
import com.mx.EmpleadosEmpresas.entidad.Empleado;

@Service
public class EmpleadosService implements MetodosEmpleados{

	@Autowired
	EmpleadoDao dao;
	@Override
	public Respuesta guardar(Empleado empleado) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Respuesta editar(Empleado empleado) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Respuesta eliminar(Empleado empleado) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Respuesta buscar(String curp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Empleado> empresas() {
		// TODO Auto-generated method stub
		return null;
	}

}
