package com.example.ytalentbackend.Services;

import com.example.ytalentbackend.Models.Jovenes;
import com.example.ytalentbackend.Models.Equipos;
import com.example.ytalentbackend.Models.Jovenesequipos;
import com.example.ytalentbackend.Models.Logros;
import com.example.ytalentbackend.Repository.JovenesequiposRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JovenesequiposService {

    @Autowired
    private JovenesequiposRepository repository;

    public List<Jovenesequipos> findAll() {
        return repository.findAll();
    }

    public Optional<Jovenesequipos> findById(Integer id) {
        return repository.findById(id);
    }

    public Jovenesequipos save(Jovenesequipos jovenesequipos) {
        return repository.save(jovenesequipos);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
    public List<Jovenesequipos> findByJovenId(Jovenes jovenesid) {
        return repository.findByJovenesid(jovenesid);
    }
    public List<Jovenesequipos> findByEquipoId(Equipos equiposid) {
        return repository.findByEquiposid(equiposid);
    }

}
