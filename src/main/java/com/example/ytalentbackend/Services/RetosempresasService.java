package com.example.ytalentbackend.Services;

import com.example.ytalentbackend.Models.Retosempresas;
import com.example.ytalentbackend.Repository.RetosempresasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RetosempresasService {

    @Autowired
    private RetosempresasRepository repository;

    public List<Retosempresas> findAll() {
        return repository.findAll();
    }

    public Optional<Retosempresas> findById(Integer id) {
        return repository.findById(id);
    }

    public Retosempresas save(Retosempresas retosempresas) {
        return repository.save(retosempresas);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
