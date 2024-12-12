package com.example.ytalentbackend.Services;

import com.example.ytalentbackend.Models.Jovenes;
import com.example.ytalentbackend.Models.Logros;
import com.example.ytalentbackend.Repository.LogrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LogrosService {

    @Autowired
    private LogrosRepository repository;

    public List<Logros> findAll() {
        return repository.findAll();
    }

    public Optional<Logros> findById(Integer id) {
        return repository.findById(id);
    }

    public Logros save(Logros logros) {
        return repository.save(logros);
    }

    public List<Logros> saveAll(List<Logros> logrosList) {
        return repository.saveAll(logrosList);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public List<Logros> findByJovenId(Jovenes jovenid) {
        return repository.findByJovenid(jovenid);
    }

}
