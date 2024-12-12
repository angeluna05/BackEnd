package com.example.ytalentbackend.Services;

import com.example.ytalentbackend.Models.Equipos;
import com.example.ytalentbackend.Repository.EquiposRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquiposService {

    @Autowired
    private EquiposRepository repository;

    public List<Equipos> findAll() {
        return repository.findAll();
    }

    public Optional<Equipos> findById(Integer id) {
        return repository.findById(id);
    }

    public Equipos save(Equipos equipos) {
        return repository.save(equipos);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
