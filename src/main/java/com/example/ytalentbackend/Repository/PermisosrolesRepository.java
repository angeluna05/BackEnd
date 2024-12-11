package com.example.ytalentbackend.Repository;

import com.example.ytalentbackend.Models.Permisosroles;
import com.example.ytalentbackend.Models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermisosrolesRepository extends JpaRepository<Permisosroles, Integer> {
    List<Permisosroles> findByRolid(Roles rolid);
}
 