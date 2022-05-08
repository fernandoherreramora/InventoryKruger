package com.kruger.app;

import com.kruger.app.dao.IEmpleadoDAO;
import com.kruger.app.dao.IUsuarioDAO;
import com.kruger.app.dto.Empleado;
import com.kruger.app.dto.Usuario;
import com.kruger.app.services.InventoryServ;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestUsuario {

    @Mock
    private IUsuarioDAO usuarioDAO;


    @InjectMocks
    private InventoryServ inventoryServ;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);

    }


    @Test
    public void TestGetUsuario(){
        Usuario usuario= new Usuario();
        Mockito.when(usuarioDAO.findByUsuario("Brando")).thenReturn(usuario);
        final Usuario user = usuarioDAO.findByUsuario("Brando");
        assertNotNull(user);
        assertEquals(user, usuario);
    }

}
