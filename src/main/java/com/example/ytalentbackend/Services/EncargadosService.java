package com.example.ytalentbackend.Services;

import com.example.ytalentbackend.Models.Encargados;
import com.example.ytalentbackend.Models.Estado;
import com.example.ytalentbackend.Repository.EncargadosRepository;
import com.example.ytalentbackend.Repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EncargadosService {

    @Autowired
    private EncargadosRepository encargadosRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    public List<Encargados> findAll() {
        return encargadosRepository.findAll();
    }

    public Optional<Encargados> findById(Integer id) {
        return encargadosRepository.findById(id);
    }

    public Encargados save(Encargados encargado) {
        return encargadosRepository.save(encargado);
    }

    public void deleteById(Integer id) {
        encargadosRepository.deleteById(id);
    }

    public Encargados updateEstado(Integer encargadoId, Integer estadoId) {
        Optional<Encargados> encargadoOptional = encargadosRepository.findById(encargadoId);
        if (encargadoOptional.isPresent()) {
            Encargados encargado = encargadoOptional.get();
            Optional<Estado> estadoOptional = estadoRepository.findById(estadoId);
            if (estadoOptional.isPresent()) {
                Estado estado = estadoOptional.get();
                encargado.setEstadoid(estado);
                return encargadosRepository.save(encargado);
            }
        }
        return null;
    }
}
