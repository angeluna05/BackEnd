//package com.example.ytalentbackend.Config;
//
//import com.example.ytalentbackend.Models.*;
//import com.example.ytalentbackend.Repository.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//@Component
//public class DataInitializer implements CommandLineRunner {
//
//    @Autowired
//    private RolesRepository rolesRepository;
//
//    @Autowired
//    private PermisosRepository permisosRepository;
//
//    @Autowired
//    private PermisosrolesRepository permisosrolesRepository;
//
//    @Autowired
//    private EstadoRepository estadoRepository; // Agregar el repositorio de Estado
//
//    @Override
//    public void run(String... args) throws Exception {
//        // Verificar y crear estado
//        Estado estadoActivo = estadoRepository.findById(1).orElseGet(() -> {
//            Estado nuevoEstado = new Estado(1); // Ajusta si el constructor es diferente
//            return estadoRepository.save(nuevoEstado);
//        });
//
//        // Crear permisos si no existen
//        List<String> permisosNombres = Stream.of(
//                "CELULAS_READ", "CELULAS_CREATE", "CELULAS_UPDATE", "CELULAS_DELETE",
//                "EMPLEADOS_READ", "EMPLEADOS_CREATE", "EMPLEADOS_UPDATE", "EMPLEADOS_DELETE",
//                "EMPRESA_READ", "EMPRESA_CREATE", "EMPRESA_UPDATE", "EMPRESA_DELETE",
//                "ENCARGADOS_READ", "ENCARGADOS_CREATE", "ENCARGADOS_UPDATE", "ENCARGADOS_DELETE",
//                "EQUIPOS_READ", "EQUIPOS_CREATE", "EQUIPOS_UPDATE", "EQUIPOS_DELETE",
//                "EQUIPOSRETOS_READ", "EQUIPOSRETOS_CREATE", "EQUIPOSRETOS_UPDATE", "EQUIPOSRETOS_DELETE",
//                "ESTADO_READ", "ESTADO_CREATE", "ESTADO_UPDATE", "ESTADO_DELETE",
//                "INSCRIPCIONESSESIONES_READ", "INSCRIPCIONESSESIONES_CREATE", "INSCRIPCIONESSESIONES_UPDATE", "INSCRIPCIONESSESIONES_DELETE",
//                "INSTITUCIONES_READ", "INSTITUCIONES_CREATE", "INSTITUCIONES_UPDATE", "INSTITUCIONES_DELETE",
//                "JOVENES_READ", "JOVENES_CREATE", "JOVENES_UPDATE", "JOVENES_DELETE",
//                "JOVENESEQUIPOS_READ", "JOVENESEQUIPOS_CREATE", "JOVENESEQUIPOS_UPDATE", "JOVENESEQUIPOS_DELETE",
//                "JOVENESINSTITUCIONES_READ", "JOVENESINSTITUCIONES_CREATE", "JOVENESINSTITUCIONES_UPDATE", "JOVENESINSTITUCIONES_DELETE",
//                "LOGROS_READ", "LOGROS_CREATE", "LOGROS_UPDATE", "LOGROS_DELETE",
//                "PERMISOS_READ", "PERMISOS_CREATE", "PERMISOS_UPDATE", "PERMISOS_DELETE",
//                "PERMISOSROLES_READ", "PERMISOSROLES_CREATE", "PERMISOSROLES_UPDATE", "PERMISOSROLES_DELETE",
//                "PROGRAMARSESION_READ", "PROGRAMARSESION_CREATE", "PROGRAMARSESION_UPDATE", "PROGRAMARSESION_DELETE",
//                "RETOS_READ", "RETOS_CREATE", "RETOS_UPDATE", "RETOS_DELETE",
//                "RETOSEMPRESAS_READ", "RETOSEMPRESAS_CREATE", "RETOSEMPRESAS_UPDATE", "RETOSEMPRESAS_DELETE",
//                "ROLES_READ", "ROLES_CREATE", "ROLES_UPDATE", "ROLES_DELETE",
//                "TIPODOCUMENTO_READ", "TIPODOCUMENTO_CREATE", "TIPODOCUMENTO_UPDATE", "TIPODOCUMENTO_DELETE",
//                "usuarios_READ", "usuarios_CREATE", "usuarios_UPDATE", "usuarios_DELETE"
//        ).toList();
//
//
//        for (String nombre : permisosNombres) {
//            List<Permisos> permisosExistentes = permisosRepository.findByNombre(nombre);
//            if (permisosExistentes.isEmpty()) {
//                Permisos permiso = new Permisos();
//                permiso.setNombre(nombre);
//                permisosRepository.save(permiso);
//            }
//        }
//
//        // Crear roles
//        Roles adminRole = new Roles();
//        adminRole.setNombre("ADMIN");
//        adminRole.setEstadoid(estadoActivo);
//        rolesRepository.save(adminRole);
//
//        Roles jovenRole = new Roles();
//        jovenRole.setNombre("JOVEN");
//        jovenRole.setEstadoid(estadoActivo);
//        rolesRepository.save(jovenRole);
//
//        Roles empleadoRole = new Roles();
//        empleadoRole.setNombre("EMPLEADO");
//        empleadoRole.setEstadoid(estadoActivo);
//        rolesRepository.save(empleadoRole);
//
//        // Asignar permisos a roles
//        Set<Permisosroles> adminPermisosRoles = permisosRepository.findAll().stream()
//                .map(permiso -> new Permisosroles(adminRole, permiso))
//                .collect(Collectors.toSet());
//        permisosrolesRepository.saveAll(adminPermisosRoles);
//
//        Set<Permisosroles> jovenPermisosRoles = permisosRepository.findAll().stream()
//                .map(permiso -> new Permisosroles(jovenRole, permiso))
//                .collect(Collectors.toSet());
//        permisosrolesRepository.saveAll(jovenPermisosRoles);
//
//        Set<Permisosroles> empleadoPermisosRoles = permisosRepository.findAll().stream()
//                .map(permiso -> new Permisosroles(empleadoRole, permiso))
//                .collect(Collectors.toSet());
//        permisosrolesRepository.saveAll(empleadoPermisosRoles);
//    }
//}
