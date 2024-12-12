package com.example.ytalentbackend.Services;

import com.example.ytalentbackend.Models.Programarsesion;
import com.example.ytalentbackend.Repository.ProgramarsesionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgramarsesionService {

    @Autowired
    private ProgramarsesionRepository repository;

    public List<Programarsesion> findAll() {
        return repository.findAll();
    }

    public Optional<Programarsesion> findById(Integer id) {
        return repository.findById(id);
    }

    public Programarsesion save(Programarsesion programarsesion) {
        return repository.save(programarsesion);
    }

    public List<Programarsesion> saveAll(List<Programarsesion> programarsesiones) {
        return repository.saveAll(programarsesiones);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public Programarsesion updateEstado(Integer id, String estado) {
        Optional<Programarsesion> optionalProgramarsesion = repository.findById(id);
        if (optionalProgramarsesion.isPresent()) {
            Programarsesion programarsesion = optionalProgramarsesion.get();
            programarsesion.setEstado(estado);
            return repository.save(programarsesion);
        } else {
            return null;
        }
    }
}
