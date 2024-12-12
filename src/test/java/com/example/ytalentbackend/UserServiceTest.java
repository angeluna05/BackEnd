package com.example.ytalentbackend;

import com.example.ytalentbackend.Models.Empresa;
import com.example.ytalentbackend.Models.Estado;
import com.example.ytalentbackend.Repository.EmpresaRepository;
import com.example.ytalentbackend.Repository.EstadoRepository;
import com.example.ytalentbackend.Services.EmpresaService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class YtalentBackendApplicationTests {

    @InjectMocks
    private EmpresaService empresaService;

    @Mock
    private EmpresaRepository empresaRepository;

    @Mock
    private EstadoRepository estadoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        empresaService.findAll();
        verify(empresaRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        Integer empresaId = 1;
        empresaService.findById(empresaId);
        verify(empresaRepository, times(1)).findById(empresaId);
    }

    @Test
    void testSave() {
        Empresa empresa = new Empresa();
        empresa.setNombre("Empresa de Prueba");
        when(empresaRepository.save(empresa)).thenReturn(empresa);

        Empresa result = empresaService.save(empresa);
        assertNotNull(result);
        assertEquals("Empresa de Prueba2", result.getNombre());
        verify(empresaRepository, times(1)).save(empresa);
    }

    @Test
    void testDeleteById() {
        Integer empresaId = 1;
        empresaService.deleteById(empresaId);
        verify(empresaRepository, times(1)).deleteById(empresaId);
    }

    @Test
    void testUpdateEstado_Success() {
        Integer empresaId = 1;
        Integer estadoId = 1;

        Empresa empresa = new Empresa();
        Estado estado = new Estado();

        when(empresaRepository.findById(empresaId)).thenReturn(Optional.of(empresa));
        when(estadoRepository.findById(estadoId)).thenReturn(Optional.of(estado));
        when(empresaRepository.save(empresa)).thenReturn(empresa);

        Empresa result = empresaService.updateEstado(empresaId, estadoId);

        assertNotNull(result);
        assertEquals(estado, result.getEstadoid());
        verify(empresaRepository, times(1)).save(empresa);
    }

    @Test
    void testUpdateEstado_Failure() {
        Integer empresaId = 1;
        Integer estadoId = 1;

        when(empresaRepository.findById(empresaId)).thenReturn(Optional.empty());

        Empresa result = empresaService.updateEstado(empresaId, estadoId);

        assertNull(result);
        verify(empresaRepository, times(0)).save(any());
    }
    
}
