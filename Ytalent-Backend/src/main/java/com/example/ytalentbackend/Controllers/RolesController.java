package com.example.ytalentbackend.Controllers;

import com.example.ytalentbackend.Models.Empresa;
import com.example.ytalentbackend.Models.Roles;
import com.example.ytalentbackend.Services.RolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor

public class RolesController {
    @Autowired
    private RolesService service;

    @GetMapping
    public List<Roles> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Roles> getById(@PathVariable Integer id) {
        Optional<Roles> roles = service.findById(id);
        return roles.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping()
    public Roles create(@RequestBody Roles roles) {
        return service.save(roles);
    }


    @PutMapping("/{id}")
    public Roles update(@PathVariable Integer id, @RequestBody Roles roles) {
        roles.setId(id);
        return service.save(roles);
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Roles> updateEstado(@PathVariable Integer id, @RequestBody Integer estadoId) {
        Roles updatedroles = service.updateEstado(id, estadoId);
        if (updatedroles != null) {
            return ResponseEntity.ok(updatedroles);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
