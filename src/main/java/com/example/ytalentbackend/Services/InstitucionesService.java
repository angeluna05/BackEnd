package com.example.ytalentbackend.Services;

import com.example.ytalentbackend.Models.Instituciones;
import com.example.ytalentbackend.Models.Estado;
import com.example.ytalentbackend.Repository.InstitucionesRepository;
import com.example.ytalentbackend.Repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstitucionesService {

    @Autowired
    private InstitucionesRepository repository;

    @Autowired
    private EstadoRepository estadoRepository;

    public List<Instituciones> findAll() {
        return repository.findAll();
    }

    public Optional<Instituciones> findById(Integer id) {
        return repository.findById(id);
    }

    public Instituciones save(Instituciones instituciones) {
        return repository.save(instituciones);
    }

    public List<Instituciones> saveAll(List<Instituciones> institucionesList) {
        return repository.saveAll(institucionesList);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public Instituciones updateEstado(Integer id, Integer estadoId) {
        Optional<Instituciones> optionalInstituciones = repository.findById(id);
        Optional<Estado> optionalEstado = estadoRepository.findById(estadoId);

        if (optionalInstituciones.isPresent() && optionalEstado.isPresent()) {
            Instituciones instituciones = optionalInstituciones.get();
            Estado estado = optionalEstado.get();
            instituciones.setEstadoid(estado);
            return repository.save(instituciones);
        } else {
            return null;
        }
    }
}
