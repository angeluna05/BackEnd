package com.example.ytalentbackend.Services;


import com.example.ytalentbackend.Models.InscripcionCelulas;
import com.example.ytalentbackend.Models.Jovenes;
import com.example.ytalentbackend.Repository.InscripcionCelulasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InscripcionCelulasService {

    @Autowired
    private InscripcionCelulasRepository repository;

    public List<InscripcionCelulas> findAll() {
        return repository.findAll();
    }

    public Optional<InscripcionCelulas> findById(Integer id) {
        return repository.findById(id);
    }

    public InscripcionCelulas save(InscripcionCelulas inscripcionCelulas) {
        return repository.save(inscripcionCelulas);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }


}
