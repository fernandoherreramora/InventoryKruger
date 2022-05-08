package com.kruger.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.kruger.app.dto.Usuario;
import com.kruger.app.dto.Vacunacion;
import com.kruger.app.services.InventoryServ;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.kruger.app.dao.IEmpleadoDAO;
import com.kruger.app.dto.Empleado;
import com.kruger.app.model.Response;

import static org.junit.Assert.*;

public class TestEmpleadoService {
	
	@Mock
	private IEmpleadoDAO empleadoDAO;


	@InjectMocks
	private InventoryServ inventoryServ;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
		
	}
	
	@Test
	public void TestListarEmpleados(){
		List<Empleado>empleados=new ArrayList<>();
		Mockito.when(empleadoDAO.findAll()).thenReturn(empleados);
		final ResponseEntity<Response> response = inventoryServ.listarEmpleados();
		assertNotNull(response);
		assertEquals(response.getBody().getCode(),Integer.valueOf(200));
	}

	@Test
	public void TestListarEmpleadosError(){
		List<Empleado>empleados=new ArrayList<>();
		Mockito.when(empleadoDAO.findAll()).thenReturn(null);
		final ResponseEntity<Response> response = inventoryServ.listarEmpleados();
		assertNotNull(response);
		assertEquals(response.getBody().getCode(),Integer.valueOf(400));
	}

	@Test
	public void TestGetUsuario(){
		Usuario usuario= new Usuario();
		Empleado empleado= new Empleado();
		Mockito.when(empleadoDAO.findByUsuario(usuario)).thenReturn(null);
		final ResponseEntity<Response> response = inventoryServ.getInfoEmpleado("Brando");
		assertNotNull(response);
		assertEquals(response.getBody().getCode(),Integer.valueOf(400));
	}

	@Test
	public void TestGetEmpleadoByUsuario(){
		Usuario usuario= new Usuario();
		Empleado empleado= new Empleado();
		Mockito.when(empleadoDAO.findByUsuario(usuario)).thenReturn(empleado);
		final Empleado empleado1 = empleadoDAO.findByUsuario(usuario);
		assertNotNull(empleado1);
		assertEquals(empleado1, empleado);
	}
}
