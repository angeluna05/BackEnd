package com.example.ytalentbackend.Services;

import com.example.ytalentbackend.Models.Celulas;
import com.example.ytalentbackend.Repository.CelulasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CelulasService {

    @Autowired
    private CelulasRepository repository;

    public List<Celulas> findAll() {
        return repository.findAll();
    }

    public Optional<Celulas> findById(Integer id) {
        return repository.findById(id);
    }

    public Celulas save(Celulas celula) {
        return repository.save(celula);
    }

    public List<Celulas> saveAll(List<Celulas> celulas) {
        return repository.saveAll(celulas);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public Celulas updateEstado(Integer id, String estado) {
        Optional<Celulas> optionalCelula = repository.findById(id);
        if (optionalCelula.isPresent()) {
            Celulas celula = optionalCelula.get();
            celula.setEstado(estado);
            return repository.save(celula);
        } else {
            return null;
        }
    }
}
