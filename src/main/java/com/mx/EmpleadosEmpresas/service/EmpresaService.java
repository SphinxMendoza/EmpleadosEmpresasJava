package com.mx.EmpleadosEmpresas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.EmpleadosEmpresas.dao.EmpresaDao;
import com.mx.EmpleadosEmpresas.dtos.Respuesta;
import com.mx.EmpleadosEmpresas.entidad.Empresa;

@Service
public class EmpresaService implements MetodosEmpresas {

	@Autowired
	EmpresaDao dao;

	@Override
	public Respuesta guardar(Empresa empresa) {
		// TODO Auto-generated method stub
		Respuesta rs = new Respuesta();
		if (dao.existsById(empresa.getRfc())) {
			rs.setMensaje("El rfc de la empresa ya esta registrada");
			rs.setSuccess(false);
			rs.setObj(empresa.getRfc());
			return rs;
		}
		for (Empresa e : dao.findAll()) {
			if (empresa.getNombre().equalsIgnoreCase(e.getNombre())) {
				rs.setMensaje("El nombre de la empresa ya esta registrada");
				rs.setSuccess(false);
				rs.setObj(empresa.getNombre());
				return rs;
			}
		}
		empresa = convertirMayusculas(empresa);
		dao.save(empresa);
		rs.setMensaje("La empresa se registro correctamente");
		rs.setSuccess(false);
		rs.setObj(empresa);
		return rs;
	}

	@Override
	public Respuesta editar(Empresa empresa) {
		// TODO Auto-generated method stub
		Respuesta rs = new Respuesta();
		Empresa aux = dao.findById(empresa.getRfc()).orElse(null);
		if (aux == null) {
			rs.setMensaje("La empresa que tratas de editar no existe");
			rs.setSuccess(false);
			rs.setObj(empresa.getRfc());
			return rs;
		}
		empresa = convertirMayusculas(empresa);

		aux.setNombre(empresa.getNombre());
		aux.setPais(empresa.getPais());
		aux.setAnioFundacion(empresa.getAnioFundacion());
		aux.setCapital(empresa.getCapital());
		aux.setTipo(empresa.getTipo());

		rs.setMensaje("La empresa ha sido editada");
		rs.setSuccess(true);
		rs.setObj(empresa);
		return rs;
	}

	@Override
	public Respuesta eliminar(Empresa empresa) {
		// TODO Auto-generated method stub
		Respuesta rs = new Respuesta();
		String rfc = empresa.getRfc();
		empresa = dao.findById(empresa.getRfc()).orElse(null);
		if (empresa != null) {
			if (empresa.getEmpleados().isEmpty()) {
				dao.delete(empresa);
				rs.setMensaje("La empresa ha sido eliminada");
				rs.setSuccess(true);
				rs.setObj(empresa);
				return rs;
			}
			rs.setMensaje("La empresa aun tiene empleadso y no se puede eliminar");
			rs.setSuccess(false);
			rs.setObj(empresa.getEmpleados());
			return rs;
		}
		rs.setMensaje("La empresa no existe");
		rs.setSuccess(false);
		rs.setObj(rfc);
		return rs;
	}

	@Override
	public Respuesta buscar(String rfc) {
		// TODO Auto-generated method stub
		Respuesta rs = new Respuesta();
		Empresa empresa = dao.findById(rfc).orElse(null);
		if (empresa == null) {
			rs.setMensaje("La empresa no existe");
			rs.setSuccess(false);
			rs.setObj(rfc);
			return rs;
		}
		rs.setMensaje("La empresa ha sido encontrada");
		rs.setSuccess(true);
		rs.setObj(empresa);
		return rs;
	}

	@Override
	public List<Empresa> listar() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	public Empresa convertirMayusculas(Empresa empresa) {
		empresa.setRfc(empresa.getRfc().toUpperCase());
		empresa.setNombre(empresa.getNombre().toUpperCase());
		empresa.setPais(empresa.getPais().toUpperCase());
		empresa.setTipo(empresa.getTipo().toUpperCase());
		return empresa;
	}
}
