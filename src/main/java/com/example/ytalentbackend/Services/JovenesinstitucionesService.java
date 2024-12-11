package com.example.ytalentbackend.Services;

import com.example.ytalentbackend.Models.Jovenesinstituciones;
import com.example.ytalentbackend.Repository.JovenesinstitucionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JovenesinstitucionesService {

    @Autowired
    private JovenesinstitucionesRepository repository;

    public List<Jovenesinstituciones> findAll() {
        return repository.findAll();
    }

    public Optional<Jovenesinstituciones> findById(Integer id) {
        return repository.findById(id);
    }

    public Jovenesinstituciones save(Jovenesinstituciones jovenesinstituciones) {
        return repository.save(jovenesinstituciones);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
