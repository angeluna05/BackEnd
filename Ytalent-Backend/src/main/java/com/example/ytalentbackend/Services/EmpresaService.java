package com.example.ytalentbackend.Services;

import com.example.ytalentbackend.Models.Empresa;
import com.example.ytalentbackend.Models.Estado;
import com.example.ytalentbackend.Repository.EmpresaRepository;
import com.example.ytalentbackend.Repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    public List<Empresa> findAll() {
        return empresaRepository.findAll();
    }

    public Optional<Empresa> findById(Integer id) {
        return empresaRepository.findById(id);
    }

    public Empresa save(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    public void deleteById(Integer id) {
        empresaRepository.deleteById(id);
    }

    public Empresa updateEstado(Integer empresaId, Integer estadoId) {
        Optional<Empresa> empresaOptional = empresaRepository.findById(empresaId);
        if (empresaOptional.isPresent()) {
            Empresa empresa = empresaOptional.get();
            Optional<Estado> estadoOptional = estadoRepository.findById(estadoId);
            if (estadoOptional.isPresent()) {
                Estado estado = estadoOptional.get();
                empresa.setEstadoid(estado);
                return empresaRepository.save(empresa);
            }
        }
        return null;
    }
}
