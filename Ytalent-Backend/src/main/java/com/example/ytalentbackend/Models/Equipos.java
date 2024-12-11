package com.example.ytalentbackend.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "equipos")
public class Equipos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "equiposid", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    public Equipos(Integer id) {
        this.id = id;
    }

    public Equipos() {

    }
}