package com.example.ytalentbackend.User;

import com.example.ytalentbackend.Models.Usuarios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UsuariosRepository extends JpaRepository<Usuarios,Integer> {
    Optional<Usuarios> findByCorreo(String correo);
    Optional<Usuarios> findById(Integer id);

     @Query(value = "SELECT p.nombre " +
             "FROM usuarios us " +
             "INNER JOIN roles r ON us.rolid = r.rolid " +
             "INNER JOIN permisosRoles pr ON r.rolid = pr.rolid " +
             "INNER JOIN permisos p ON p.permisosid = pr.permisosid " +
             "WHERE us.usuarioid = :usuarioid", nativeQuery = true)
     List<String> findPermisosByUsuarioId(@Param("usuarioid") Integer usuarioId);
}
