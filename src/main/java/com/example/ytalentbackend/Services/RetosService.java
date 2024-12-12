package com.example.ytalentbackend.Services;

import com.example.ytalentbackend.Models.Retos;
import com.example.ytalentbackend.Models.Estado;
import com.example.ytalentbackend.Repository.RetosRepository;
import com.example.ytalentbackend.Repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RetosService {

    @Autowired
    private RetosRepository repository;

    @Autowired
    private EstadoRepository estadoRepository;

    public List<Retos> findAll() {
        return repository.findAll();
    }

    public Optional<Retos> findById(Integer id) {
        return repository.findById(id);
    }

    public Retos save(Retos retos) {
        return repository.save(retos);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public Retos updateEstado(Integer id, String estado) {
        Optional<Retos> optionalRetos = repository.findById(id);
        Optional<Retos> optionalEstado = repository.findById(id);

        if (optionalRetos.isPresent() && optionalEstado.isPresent()) {
            Retos retos = optionalRetos.get();
            retos.setEstado(estado);
            return repository.save(retos);
        } else {
            return null;
        }
    }
}
