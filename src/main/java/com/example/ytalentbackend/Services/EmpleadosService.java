package com.example.ytalentbackend.Services;

import com.example.ytalentbackend.Models.Empleados;
import com.example.ytalentbackend.Models.Estado;
import com.example.ytalentbackend.Repository.EmpleadosRepository;
import com.example.ytalentbackend.Repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadosService {

    @Autowired
    private EmpleadosRepository empleadosRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    public List<Empleados> findAll() {
        return empleadosRepository.findAll();
    }

    public Optional<Empleados> findById(Integer id) {
        return empleadosRepository.findById(id);
    }

    public Empleados save(Empleados empleado) {
        return empleadosRepository.save(empleado);
    }

    public void deleteById(Integer id) {
        empleadosRepository.deleteById(id);
    }

    public Empleados changeEstado(Integer empleadoId, Integer nuevoEstadoId) {
        Optional<Empleados> empleadoOpt = empleadosRepository.findById(empleadoId);
        Optional<Estado> estadoOpt = estadoRepository.findById(nuevoEstadoId);
        if (empleadoOpt.isPresent() && estadoOpt.isPresent()) {
            Empleados empleado = empleadoOpt.get();
            Estado nuevoEstado = estadoOpt.get();
            empleado.setEstadoid(nuevoEstado);
            return empleadosRepository.save(empleado);
        }
        return null;
    }
}