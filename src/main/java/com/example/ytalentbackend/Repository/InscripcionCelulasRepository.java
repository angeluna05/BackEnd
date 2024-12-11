package com.example.ytalentbackend.Repository;


import com.example.ytalentbackend.Models.InscripcionCelulas;
import com.example.ytalentbackend.Models.Jovenes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InscripcionCelulasRepository extends JpaRepository<InscripcionCelulas, Integer> {
}
