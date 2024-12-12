package com.example.ytalentbackend.Controllers;

import com.example.ytalentbackend.Models.Jovenes;
import com.example.ytalentbackend.Models.Equipos;
import com.example.ytalentbackend.Models.Jovenesequipos;
import com.example.ytalentbackend.Models.Logros;
import com.example.ytalentbackend.Services.JovenesequiposService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jovenesequipos")
@RequiredArgsConstructor

public class JovenesequiposController {

    @Autowired
    private JovenesequiposService service;

    @GetMapping
    public List<Jovenesequipos> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jovenesequipos> getById(@PathVariable Integer id) {
        Optional<Jovenesequipos> jovenesequipos = service.findById(id);
        return jovenesequipos.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/joven/{id}")
    public List<Jovenesequipos> getEquiposByJovenId(@PathVariable Integer id) {
        Jovenes jovenes = new Jovenes();
        jovenes.setId(id);
        return service.findByJovenId(jovenes);
    }

    @GetMapping("/equipo/{id}")
    public List<Jovenesequipos> getJovenesByEquipoId(@PathVariable Integer id) {
        Equipos equipos = new Equipos();
        equipos.setId(id);
        return service.findByEquipoId(equipos);
    }


    @PostMapping
    public Jovenesequipos create(@RequestBody Jovenesequipos jovenesequipos) {
        return service.save(jovenesequipos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jovenesequipos> update(@PathVariable Integer id, @RequestBody Jovenesequipos jovenesequipos) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        jovenesequipos.setId(id);
        Jovenesequipos updated = service.save(jovenesequipos);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
