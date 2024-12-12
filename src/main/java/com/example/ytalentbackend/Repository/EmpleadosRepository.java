package com.example.ytalentbackend.Repository;

import com.example.ytalentbackend.Models.Empleados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadosRepository extends JpaRepository<Empleados, Integer> {
}
