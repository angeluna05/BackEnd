package com.example.ytalentbackend.Repository;

import com.example.ytalentbackend.Models.Equipos;
import com.example.ytalentbackend.Models.Equiposretos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquiposretosRepository extends JpaRepository<Equiposretos, Integer> {
    List<Equiposretos> findByEquiposid(Equipos equiposid);

}
