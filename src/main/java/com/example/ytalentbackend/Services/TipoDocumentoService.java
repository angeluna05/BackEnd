package com.example.ytalentbackend.Services;

import com.example.ytalentbackend.Models.Tipodocumento;
import com.example.ytalentbackend.Repository.TipoDocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoDocumentoService {

    @Autowired
    private TipoDocumentoRepository tipoDocumentoRepository;

    public List<Tipodocumento> getAllTipoDocumentos() {
        return tipoDocumentoRepository.findAll();
    }

    public Optional<Tipodocumento> getTipoDocumentoById(Integer id) {
        return tipoDocumentoRepository.findById(id);
    }

    public Tipodocumento saveTipoDocumento(Tipodocumento Tipodocumento) {
        return tipoDocumentoRepository.save(Tipodocumento);
    }

    public void deleteTipoDocumento(Integer id) {
        tipoDocumentoRepository.deleteById(id);
    }
}
