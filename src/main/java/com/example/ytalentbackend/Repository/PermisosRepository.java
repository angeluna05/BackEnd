package com.example.ytalentbackend.Repository;

import com.example.ytalentbackend.Models.Permisos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermisosRepository extends JpaRepository<Permisos, Integer> {
    Optional<Permisos> findByNombre(String nombre);
}
