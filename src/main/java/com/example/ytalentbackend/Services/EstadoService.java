package com.example.ytalentbackend.Services;

import com.example.ytalentbackend.Models.Estado;
import com.example.ytalentbackend.Repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository repository;

    public List<Estado> findAll() {
        return repository.findAll();
    }

    public Optional<Estado> findById(Integer id) {
        return repository.findById(id);
    }

    public Estado save(Estado estado) {
        return repository.save(estado);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
