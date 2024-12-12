package com.example.ytalentbackend.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ytalentbackend.Models.Estado;
import com.example.ytalentbackend.Models.Jovenes;
import com.example.ytalentbackend.Repository.EstadoRepository;
import com.example.ytalentbackend.Repository.JovenesRepository;

@Service
public class JovenesService {

    @Autowired
    private final EstadoRepository estadoRepository;

    @Autowired

    private final JovenesRepository repository;

    @Autowired
    public JovenesService(JovenesRepository repository, EstadoRepository estadoRepository) {
        this.repository = repository;
        this.estadoRepository = estadoRepository;
    }

    public List<Jovenes> findAll() {
        return repository.findAll();
    }

    public Optional<Jovenes> findById(Integer id) {
        return repository.findById(id);
    }

    public Jovenes save(Jovenes jovenes) {
        return repository.save(jovenes);
    }

    public List<Jovenes> saveAll(List<Jovenes> jovenesList) {
        return repository.saveAll(jovenesList);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public Jovenes updateEstado(Integer id, Integer estadoId) {
        Optional<Jovenes> optionalJovenes = repository.findById(id);
        Optional<Estado> optionalEstado = estadoRepository.findById(estadoId);

        if (optionalJovenes.isPresent() && optionalEstado.isPresent()) {
            Jovenes jovenes = optionalJovenes.get();
            Estado estado = optionalEstado.get();
            jovenes.setEstadoid(estado);
            return repository.save(jovenes);
        } else {
            return null;
        }
    }
}
