package com.example.ytalentbackend.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "inscripcion_celulas")
public class InscripcionCelulas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inscripcion_celulasid", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "celulaid", nullable = false)
    private Celulas celulaid;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "jovenid", nullable = false)
    private Jovenes jovenid;
    
    @Column(name = "estado", nullable = false, length = 50)
    private String estado;
    public InscripcionCelulas(Integer id) {
        this.id = id;
    }

    public InscripcionCelulas() {

    }
}