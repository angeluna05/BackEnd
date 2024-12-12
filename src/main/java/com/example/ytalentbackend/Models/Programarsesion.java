package com.example.ytalentbackend.Models;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "programarsesion")
public class Programarsesion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "programar_sesionid", nullable = false)
    private Integer id;

    @NotBlank(message = "El titulo no puede estar vacío")
    @Size(max = 200, message = "El titulo no puede tener más de 200 caracteres")
    @Column(name = "titulo", nullable = false, length = 500)
    private String titulo;

    @NotNull(message = "La fecha no puede estar vacía")
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @NotNull(message = "La hora de inicio no puede estar vacía")
    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @NotNull(message = "La hora de fin no puede estar vacía")
    @Column(name = "hora_fin", nullable = false)
    private LocalTime horaFin;

    @Size(max = 500, message = "El link no puede tener más de 500 caracteres")
    @Column(name = "link", length = 500)
    private String link;

    @Size(max = 250, message = "El lugar no puede tener más de 250 caracteres")
    @Column(name = "lugar", length = 250)
    private String lugar;

    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(max = 500, message = "La descripción no puede tener más de 500 caracteres")
    @Column(name = "descripcion", nullable = false, length = 500)
    private String descripcion;

    @NotBlank(message = "El tipo de acceso no puede estar vacío")
    @Pattern(regexp = "Abierta|Cerrada", message = "El tipo de acceso debe ser 'Abierta' o 'Cerrada'")
    @Column(name = "tipo_acceso", nullable = false)
    private String tipo_acceso;

    @NotBlank(message = "El estado no puede estar vacío")
    @Size(max = 50, message = "El estado no puede tener más de 50 caracteres")
    @Column(name = "estado", nullable = false, length = 50)
    private String estado;

    public Programarsesion() {

    }

    public Programarsesion(Integer id) {
        this.id = id;
    }
    

}
