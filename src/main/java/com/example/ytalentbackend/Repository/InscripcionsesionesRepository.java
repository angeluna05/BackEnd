package com.example.ytalentbackend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ytalentbackend.Models.Inscripcionsesiones;
import com.example.ytalentbackend.Models.Programarsesion;

public interface InscripcionsesionesRepository extends JpaRepository<Inscripcionsesiones, Integer> {
    // Método para buscar por sesionesid
    List<Inscripcionsesiones> findByProgramarSesionid(Programarsesion programarsesion);
}
