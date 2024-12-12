package com.example.ytalentbackend.Repository;

import com.example.ytalentbackend.Models.Tipodocumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoDocumentoRepository extends JpaRepository<Tipodocumento, Integer> {
}
