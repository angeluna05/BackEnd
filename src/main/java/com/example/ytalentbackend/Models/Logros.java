package com.example.ytalentbackend.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "logros")
public class Logros {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "logroid", nullable = false)
    private Integer id;

    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(max = 500, message = "La descripción no puede exceder los 500 caracteres")
    @Column(name = "descripcion", nullable = false, length = 500)
    private String descripcion;

    @NotNull(message = "El año del logro no puede ser nulo")
    @Positive(message = "El año del logro debe ser un número positivo")
    @Column(name = "`año_logro`", nullable = false)
    private Integer anoLogro;

    @NotNull(message = "La fecha de registro no puede ser nula")
    @Column(name = "fecha_registro", nullable = false)
    private LocalDate fechaRegistro;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "jovenid", nullable = false)
    @NotNull(message = "El joven asociado no puede ser nulo")
    private Jovenes jovenid;

    public Logros(Integer id) {
        this.id = id;
    }

    public Logros() {

    }
}
