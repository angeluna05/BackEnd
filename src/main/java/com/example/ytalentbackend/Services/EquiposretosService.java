package com.example.ytalentbackend.Services;

import com.example.ytalentbackend.Models.Equipos;
import com.example.ytalentbackend.Models.Equiposretos;
import com.example.ytalentbackend.Repository.EquiposretosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquiposretosService {

    @Autowired
    private EquiposretosRepository repository;

    public List<Equiposretos> findAll() {
        return repository.findAll();
    }

    public Optional<Equiposretos> findById(Integer id) {
        return repository.findById(id);
    }

    public Equiposretos save(Equiposretos equiposretos) {
        return repository.save(equiposretos);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
    public List<Equiposretos> findByEquipoid(Equipos equiposid) {
        return repository.findByEquiposid(equiposid);
    }

}
