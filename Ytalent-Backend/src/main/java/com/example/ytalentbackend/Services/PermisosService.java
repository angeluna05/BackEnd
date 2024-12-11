package com.example.ytalentbackend.Services;

import com.example.ytalentbackend.Models.Permisos;
import com.example.ytalentbackend.Models.Permisosroles;
import com.example.ytalentbackend.Repository.PermisosRepository;
import com.example.ytalentbackend.Repository.PermisosrolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.*;

@Service
public class PermisosService {

    @Autowired
    private PermisosRepository repository;

    public List<Permisos> findAll() {
        return repository.findAll();
    }

    public Optional<Permisos> findById(Integer id) {
        return repository.findById(id);
    }

    public Permisos save(Permisos permisos) {
        return repository.save(permisos);
    }

    public List<Permisos> saveAll(List<Permisos> permisosList) {
        return repository.saveAll(permisosList);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
