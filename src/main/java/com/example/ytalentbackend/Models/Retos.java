package com.example.ytalentbackend.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;


@Getter
@Setter
@Entity
@Table(name = "retos")
public class Retos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "retoid", nullable = false)
    private Integer id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 200, message = "El nombre no puede tener más de 200 caracteres")
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(max = 500, message = "La descripción no puede tener más de 500 caracteres")
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @NotNull(message = "La fecha de inicio no puede estar vacía")
    //@FutureOrPresent(message = "La fecha de inicio no puede ser anterior a la fecha actual")
    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @NotNull(message = "La fecha de fin no puede estar vacía")
    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    @NotNull(message = "La fecha de inicio de inscripción no puede estar vacía")
    @Column(name = "inicio_inscripcion", nullable = false)
    private LocalDate inicioInscripcion;

    @NotNull(message = "La fecha de fin de inscripción no puede estar vacía")
    @Column(name = "fin_inscripcion", nullable = false)
    private LocalDate finInscripcion;
/*
    @NotNull(message = "El máximo de personas no puede estar vacío")
    @Min(value = 1, message = "El máximo de personas debe ser al menos 1")
    @Column(name = "maximo_personas", nullable = false)
    private Integer maximoPersonas;

    @NotNull(message = "El máximo de personas por equipo no puede estar vacío")
    @Min(value = 1, message = "El máximo de personas por equipo debe ser al menos 1")
    @ColumnDefault("7")
    @Column(name = "maximo_personas_equipo", nullable = false)
    private Integer maximoPersonasEquipo;*/

    @NotBlank(message = "El tipo de acceso no puede estar vacío")
    @Pattern(regexp = "Abierto|Cerrado", message = "El tipo de acceso debe ser 'Abierto' o 'Cerrado'")
    @Column(name = "tipo_acceso", nullable = false)
    private String tipo_acceso;

    @NotBlank(message = "El estado no puede estar vacío")
    @Size(max = 50, message = "El estado no puede tener más de 50 caracteres")
    @Column(name = "estado", nullable = false)
    private String estado;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "empleadoid", nullable = false)
    private Empleados empleadoid;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "encargadoid", nullable = false)
    private Encargados encargadoid;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "empresaid", nullable = false)
    private Empresa empresaid;

    public Retos() {

    }

    public Retos(Integer id) {
        this.id = id;
    }
}
