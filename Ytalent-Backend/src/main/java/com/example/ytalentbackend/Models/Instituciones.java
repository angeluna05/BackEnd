package com.example.ytalentbackend.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "instituciones")
public class Instituciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "institucionid", nullable = false)
    private Integer id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 50, message = "El nombre no puede exceder los 50 caracteres")
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @NotBlank(message = "El rector no puede estar vacío")
    @Size(max = 50, message = "El rector no puede exceder los 50 caracteres")
    @Column(name = "rector", nullable = false, length = 50)
    private String rector;

    @NotBlank(message = "El número de contacto no puede estar vacío")
    @Size(max = 10, message = "El número de contacto no puede exceder los 10 caracteres")
    @Column(name = "numero_contacto", nullable = false, length = 20)
    private String numeroContacto;

    @NotNull(message = "El número de jóvenes activos no puede ser nulo")
    @Min(value = 0, message = "El número de jóvenes activos no puede ser negativo")
    @Column(name = "jovenes_activos", nullable = false)
    private Integer jovenesActivos;

    @NotNull(message = "El número de jóvenes egresados no puede ser nulo")
    @Min(value = 0, message = "El número de jóvenes egresados no puede ser negativo")
    @Column(name = "jovenes_egresados", nullable = false)
    private Integer jovenesEgresados;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "empleadoid", nullable = false)
    private Empleados empleadoid;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "estadoid", nullable = false)
    @NotNull(message = "El estado no puede ser nulo")
    private Estado estadoid;

    public Instituciones(Integer id) {
        this.id = id;
    }

    public Instituciones() {

    }
}
