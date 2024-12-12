package com.example.ytalentbackend.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ytalentbackend.Models.Inscripcionsesiones;
import com.example.ytalentbackend.Models.Programarsesion;
import com.example.ytalentbackend.Repository.InscripcionsesionesRepository;

@Service
public class InscripcionsesionesService {

    @Autowired
    private InscripcionsesionesRepository repository;

    public List<Inscripcionsesiones> findAll() {
        return repository.findAll();
    }
 
    public Optional<Inscripcionsesiones> findById(Integer id) {
        return repository.findById(id);
    }

    public Inscripcionsesiones save(Inscripcionsesiones inscripcionsesione) {
        return repository.save(inscripcionsesione);
    }

    public List<Inscripcionsesiones> saveAll(List<Inscripcionsesiones> inscripcionsesiones) {
        return repository.saveAll(inscripcionsesiones);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
    // Nuevo método para buscar por sesionesid
    public List<Inscripcionsesiones> findByProgramarSesionid(Programarsesion programarSesionid) {
        return repository.findByProgramarSesionid(programarSesionid);
    }
    public Optional<Inscripcionsesiones> updateEstado(Integer id, String nuevoEstado) {
        Optional<Inscripcionsesiones> optionalInscripcion = repository.findById(id);
        if (optionalInscripcion.isPresent()) {
            Inscripcionsesiones inscripcionsesione = optionalInscripcion.get();
            inscripcionsesione.setEstado(nuevoEstado);
            repository.save(inscripcionsesione);
            return Optional.of(inscripcionsesione);
        }
        return Optional.empty();
    }
}
