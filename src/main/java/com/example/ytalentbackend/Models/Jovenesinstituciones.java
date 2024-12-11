package com.example.ytalentbackend.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "jovenesinstituciones")
public class Jovenesinstituciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jovenes_institucionesid", nullable = false)
    private Integer id;

    @NotNull(message = "La fecha de inicio no puede ser nula")
    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @NotNull(message = "La fecha de fin no puede ser nula")
    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    @NotBlank(message = "El grado/semestre no puede estar vacío")
    @Size(max = 50, message = "El grado/semestre no puede exceder los 50 caracteres")
    @Column(name = "grado_semestre", nullable = false, length = 50)
    private String gradoSemestre;

    @NotBlank(message = "La especialidad no puede estar vacía")
    @Size(max = 50, message = "La especialidad no puede exceder los 50 caracteres")
    @Column(name = "especialidad", nullable = false, length = 50)
    private String especialidad;

    @NotNull(message = "La institución no puede ser nula")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "institucionid", nullable = false)
    private Instituciones institucionid;

    @NotNull(message = "El joven no puede ser nulo")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "jovenid", nullable = false)
    private Jovenes jovenid;

    public Jovenesinstituciones(Integer id) {
        this.id = id;
    }

    public Jovenesinstituciones() {

    }
}
