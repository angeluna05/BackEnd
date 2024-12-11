package com.example.ytalentbackend.Repository;

import com.example.ytalentbackend.Models.Encargados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncargadosRepository extends JpaRepository<Encargados, Integer> {
}
