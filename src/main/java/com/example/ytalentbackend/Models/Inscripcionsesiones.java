package com.example.ytalentbackend.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "inscripcionsesiones")
public class Inscripcionsesiones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inscripcion_sesionesid", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "programar_sesionid", nullable = false)
    private Programarsesion programarSesionid;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "jovenesid", nullable = false)
    private Jovenes jovenesid;

    @Column(name = "estado", nullable = false, length = 50)
    private String estado;

    public Inscripcionsesiones(Integer id) {
        this.id = id;
    }

    public Inscripcionsesiones() {

    }
}