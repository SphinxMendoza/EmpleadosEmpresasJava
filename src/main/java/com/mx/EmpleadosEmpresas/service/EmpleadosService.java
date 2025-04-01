package com.mx.EmpleadosEmpresas.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.EmpleadosEmpresas.dao.EmpleadoDao;
import com.mx.EmpleadosEmpresas.dtos.Respuesta;
import com.mx.EmpleadosEmpresas.entidad.Empleado;
import com.mx.EmpleadosEmpresas.entidad.Empresa;

@Service
public class EmpleadosService implements MetodosEmpleados {

	@Autowired
	EmpleadoDao dao;

	@Override
	public Respuesta guardar(Empleado empleado) {
		// TODO Auto-generated method stub
		Respuesta rs = new Respuesta();
		if (dao.existsById(empleado.getCurp())) {
			rs.setMensaje("La CURP ya existe resgistrado");
			rs.setSuccess(false);
			rs.setObj(empleado.getCurp());
			return rs;
		}
		for (Empleado e : dao.findAll()) {
			if (empleado.getNombreEmpleado().equalsIgnoreCase(e.getNombreEmpleado())
				&& empleado.getApellido().equalsIgnoreCase(e.getApellido())) {
				rs.setMensaje("El empleado ya existe resgistrado");
				rs.setSuccess(false);
				rs.setObj(empleado.getCurp());
				return rs;
			}
		}
		empleado = convertirMayusculas(empleado);
		dao.save(empleado);
		rs.setMensaje("El empleado se registro");
		rs.setSuccess(false);
		rs.setObj(empleado.getCurp());
		return rs;
	}

	@Override
	public Respuesta editar(Empleado empleado) {
		// TODO Auto-generated method stub
		Respuesta rs = new Respuesta();
		if (dao.existsById(empleado.getCurp())) {
			dao.save(empleado);
			rs.setMensaje("el empleado ha sido editado");
			rs.setSuccess(true);
			rs.setObj(empleado);
			return rs;
		}
		rs.setMensaje("el empleado no existe");
		rs.setSuccess(false);
		rs.setObj(empleado.getCurp());
		return rs;
	}

	@Override
	public Respuesta eliminar(Empleado empleado) {
		// TODO Auto-generated method stub
		Respuesta rs =new Respuesta();
		String curp = empleado.getCurp();
		empleado = dao.findById(empleado.getCurp()).orElse(null);
		if (empleado == null) {
			rs.setMensaje("El empleado que tratas de eliminar no existe");
			rs.setSuccess(false);
			rs.setObj(curp);
			return rs;
		}
		if (empleado.getEmpresa() != null) {
			empleado.getEmpresa().getEmpleados().remove(empleado);
			empleado.setEmpresa(null);
		}
		dao.delete(empleado);
		rs.setMensaje("El empleado se ha eliminado");
		rs.setSuccess(true);
		rs.setObj(empleado);
		return rs;
	}

	@Override
	public Respuesta buscar(String curp) {
		// TODO Auto-generated method stub
		Respuesta rs = new Respuesta();
		Empleado empleado = dao.findById(curp).orElse(null);
		if (empleado == null) {
			rs.setMensaje("El empleado que tratas de buscar no existe");
			rs.setSuccess(false);
			rs.setObj(null);
		}else {
			rs.setMensaje("El empleado ha sido encontrado");
			rs.setSuccess(true);
			rs.setObj(empleado);
		}
		return rs;
	}

	@Override
	public List<Empleado> empresas() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}
	
	public Empleado convertirMayusculas(Empleado empleado) {
		empleado.setCurp(empleado.getCurp().toUpperCase());
		empleado.setNombreEmpleado(empleado.getNombreEmpleado().toUpperCase());
		empleado.setApellido(empleado.getApellido());
		empleado.setGenero(empleado.getGenero().toUpperCase());
		empleado.setEstadoCivil(empleado.getEstadoCivil().toUpperCase());
		empleado.setCiudadResidencia(empleado.getCiudadResidencia().toUpperCase());
		return empleado;
	}

}
