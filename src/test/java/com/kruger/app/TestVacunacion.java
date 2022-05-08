package com.kruger.app;

import com.kruger.app.dao.IUsuarioDAO;
import com.kruger.app.dao.IVacunacionDAO;
import com.kruger.app.dto.Usuario;
import com.kruger.app.dto.Vacunacion;
import com.kruger.app.services.InventoryServ;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestVacunacion {

    @Mock
    private IVacunacionDAO vacunacionDAO;


    @InjectMocks
    private InventoryServ inventoryServ;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void TestGetVacunacion(){
        Vacunacion vacunacion= new Vacunacion();
        Usuario usuario= new Usuario();
        Mockito.when(vacunacionDAO.findByUsuario(usuario)).thenReturn(vacunacion);
        final Vacunacion vacc = vacunacionDAO.findByUsuario(usuario);
        assertNotNull(vacc);
        assertEquals(vacc, vacunacion);
    }
}
