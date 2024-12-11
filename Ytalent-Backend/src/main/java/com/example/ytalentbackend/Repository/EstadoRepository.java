package com.example.ytalentbackend.Repository;

import com.example.ytalentbackend.Models.Estado;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {
    @NotNull(message = "El estado no puede ser nulo") Estado findByid(Integer id);
}
