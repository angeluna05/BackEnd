package com.example.ytalentbackend.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "celulas")
public class Celulas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "celulaid", nullable = false)
    private Integer id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 200, message = "El nombre no puede tener más de 200 caracteres")
    @Column(name = "nombre", nullable = false, length = 200)
    private String nombre;

    @NotBlank(message = "El objetivo no puede estar vacío")
    @Size(max = 500, message = "El objetivo no puede tener más de 500 caracteres")
    @Column(name = "objetivo", nullable = false, length = 500)
    private String objetivo;

    @NotNull(message = "La fecha de inicio no puede estar vacía")
    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @NotNull(message = "La fecha de fin no puede estar vacía")
    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    @NotNull(message = "El máximo de personas no puede estar vacío")
    @Min(value = 1, message = "El máximo de personas debe ser al menos 1")
    @Column(name = "maximo_personas", nullable = false)
    private Integer maximoPersonas;

    @NotNull(message = "La fecha de inicio de inscripción no puede estar vacía")
    @Column(name = "inicio_inscripcion", nullable = false)
    private LocalDate inicioInscripcion;

    @NotNull(message = "La fecha de fin de inscripción no puede estar vacía")
    @Column(name = "fin_inscripcion", nullable = false)
    private LocalDate finInscripcion;

    @NotBlank(message = "El tipo de acceso no puede estar vacío")
    @Pattern(regexp = "Abierta|Cerrada", message = "El tipo de acceso debe ser 'Abierta' o 'Cerrada'")
    @Column(name = "tipo_acceso", nullable = false)
    private String tipo_acceso;

    @NotBlank(message = "El estado no puede estar vacío")
    @Size(max = 50, message = "El estado no puede tener más de 50 caracteres")
    @Column(name = "estado", nullable = false, length = 50)
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

    public Celulas() {

    }

    public class FieldValidationException extends RuntimeException {
        private final String fieldName;

        public FieldValidationException(String fieldName, String message) {
            super(message);
            this.fieldName = fieldName;
        }

        public String getFieldName() {
            return fieldName;
        }
    }

    @PrePersist
    @PreUpdate
    private void validateDates() {
        if (fechaInicio != null && fechaFin != null && fechaInicio.isAfter(fechaFin)) {
            throw new FieldValidationException("fechaFin", "La fecha fin debe ser anterior a la fecha de inicio");
        }

        if (inicioInscripcion != null && finInscripcion != null && inicioInscripcion.isAfter(finInscripcion)) {
            throw new FieldValidationException("finInscripcion", "La fecha de fin de inscripción no puede ser anterior a la fecha de inicio de inscripción");
        }


    }

    public Celulas(Integer id) {
        this.id = id;
    }
}
