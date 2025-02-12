package com.example.ytalentbackend.User;

import com.example.ytalentbackend.Models.usuarios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface usuariosRepository extends JpaRepository<usuarios,Integer> {
    Optional<usuarios> findByCorreo(String correo);
    Optional<usuarios> findById(Integer id);


    // @Query(value = "SELECT p.nombre " +
    //         "FROM usuarios us " +
    //         "INNER JOIN Roles r ON us.rolid = r.rolid " +
    //         "INNER JOIN PermisosRoles pr ON r.rolid = pr.rolid " +
    //         "INNER JOIN Permisos p ON p.permisosid = pr.permisosid " +
    //         "WHERE us.usuarioid = :usuarioid", nativeQuery = true)
    // List<String> findPermisosByUsuarioId(@Param("usuarioid") Integer usuarioId);
}
