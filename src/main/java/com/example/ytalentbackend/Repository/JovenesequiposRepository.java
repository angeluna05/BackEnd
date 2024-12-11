package com.example.ytalentbackend.Repository;

import com.example.ytalentbackend.Models.Equipos;
import com.example.ytalentbackend.Models.Jovenes;
import com.example.ytalentbackend.Models.Jovenesequipos;
import com.example.ytalentbackend.Models.Logros;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JovenesequiposRepository extends JpaRepository<Jovenesequipos, Integer> {
    List<Jovenesequipos> findByJovenesid(Jovenes jovenes);
    List<Jovenesequipos> findByEquiposid(Equipos equipos);

}
