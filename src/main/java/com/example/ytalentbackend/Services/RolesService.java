package com.example.ytalentbackend.Services;

import com.example.ytalentbackend.Models.*;
import com.example.ytalentbackend.Repository.EstadoRepository;
import com.example.ytalentbackend.Repository.RolesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.HashSet;

@Service
public class RolesService {


    @Autowired
    private RolesRepository repository;

    @Autowired
    private EstadoRepository estadoRepository;

    public List<Roles> findAll() {
        return repository.findAll();
    }

    public Optional<Roles> findById(Integer id) {
        return repository.findById(id);
    }

    public Roles save(Roles roles) {
        return repository.save(roles);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public Roles updateEstado(Integer rolesId, Integer estadoId) {
        Optional<Roles> rolesOptional = repository.findById(rolesId);
        if (rolesOptional.isPresent()) {
            Roles roles = rolesOptional.get();
            Optional<Estado> estadoOptional = estadoRepository.findById(estadoId);
            if (estadoOptional.isPresent()) {
                Estado estado = estadoOptional.get();
                roles.setEstadoid(estado);
                return repository.save(roles);
            }
        }
        return null;
    }
}
