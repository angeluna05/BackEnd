package com.example.ytalentbackend.Repository;

import com.example.ytalentbackend.Models.Jovenes;
import com.example.ytalentbackend.Models.Logros;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogrosRepository extends JpaRepository<Logros, Integer> {
    List<Logros> findByJovenid(Jovenes jovenid);

}
