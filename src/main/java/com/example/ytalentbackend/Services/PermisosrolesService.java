package com.example.ytalentbackend.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.ytalentbackend.Models.Permisos;
import com.example.ytalentbackend.Models.Permisosroles;
import com.example.ytalentbackend.Models.Roles;
import com.example.ytalentbackend.Repository.PermisosRepository;
import com.example.ytalentbackend.Repository.PermisosrolesRepository;
import com.example.ytalentbackend.Repository.RolesRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PermisosrolesService {

    private final PermisosRepository permisoRepository;
    private final RolesRepository rolRepository;
    private final PermisosrolesRepository permisosRolesRepository;

    public List<Permisos> findAll() {
        return permisoRepository.findAll(); // Cambié esto para que devuelva todos los permisos
    }

    public Optional<Permisos> findById(Integer id) {
        return permisoRepository.findById(id); // Cambié esto para buscar permisos
    }

    public Permisos save(Permisos permisos) {
        return permisoRepository.save(permisos); // Cambié esto para guardar permisos
    }

    public List<Permisos> saveAll(List<Permisos> permisosList) {
        return permisoRepository.saveAll(permisosList); // Cambié esto para guardar múltiples permisos
    }

    public void deleteById(Integer id) {
        permisoRepository.deleteById(id); // Cambié esto para eliminar permisos
    }

    @Transactional
    public List<Permisosroles> obtenerTodosLosPermisosRoles() {
        return permisosRolesRepository.findAll();
    }

// Asignar un permiso a un rol usando el ID del rol
public void asignarPermisoARol(String nombrePermiso, Integer rolId) {
    Permisos permiso = permisoRepository.findByNombre(nombrePermiso)
            .orElseThrow(() -> new RuntimeException("Permiso no encontrado"));
    Roles rol = rolRepository.findById(rolId)
            .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

    Permisosroles permisosRoles = Permisosroles.builder()
            .permisosid(permiso)
            .rolid(rol)
            .build();

    permisosRolesRepository.save(permisosRoles);
}

// Eliminar un permiso de un rol usando el ID del rol
public void eliminarPermisoDeRol(String nombrePermiso, Integer rolId) {
    Permisos permiso = permisoRepository.findByNombre(nombrePermiso)
            .orElseThrow(() -> new RuntimeException("Permiso no encontrado"));
    Roles rol = rolRepository.findById(rolId)
            .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

    Permisosroles permisosRoles = permisosRolesRepository.findByRolid(rol).stream()
            .filter(pr -> pr.getPermisosid().equals(permiso))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Asignación no encontrada"));

    permisosRolesRepository.delete(permisosRoles);
}

// Listar permisos de un rol por su ID
public List<Permisos> listarPermisosPorRolId(Integer rolId) {
    Roles rol = rolRepository.findById(rolId)
            .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
    return permisosRolesRepository.findByRolid(rol).stream()
            .map(Permisosroles::getPermisosid)
            .collect(Collectors.toList());
}

}
