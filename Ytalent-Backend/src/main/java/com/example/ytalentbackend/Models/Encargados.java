package com.example.ytalentbackend.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "encargados")
public class Encargados {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "encargadoid", nullable = false)
    private Integer id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 50, message = "El nombre no puede exceder los 50 caracteres")
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacío")
    @Size(max = 50, message = "El apellido no puede exceder los 50 caracteres")
    @Column(name = "apellido", nullable = false, length = 50)
    private String apellido;

    @NotBlank(message = "El número de contacto no puede estar vacío")
    @Size(max = 10, message = "El número de contacto no puede exceder los 10 caracteres")
    @Column(name = "numero_contacto", nullable = false, length = 20)
    private String numeroContacto;

    @NotNull(message = "El número de documento no puede estar vacío")
    @Min(value = 99999, message = "El documento debe tener al menos 6 dígitos.")
    @Column(name = "documento", nullable = false)
    private Integer documento;

    @NotNull(message = "La fecha de nacimiento no puede estar vacía")
    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @NotBlank(message = "El cargo no puede estar vacío")
    @Size(max = 50, message = "El cargo no puede exceder los 50 caracteres")
    @Column(name = "cargo", nullable = false, length = 50)
    private String cargo;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "tipo_documentoid", nullable = false)
    @NotNull(message = "El tipo de documento no puede estar vacío")
    private Tipodocumento tipoDocumentoid;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "estadoid", nullable = false)
    @NotNull(message = "El estado no puede ser nulo")
    private Estado estadoid;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "empresaid", nullable = false)
    @NotNull(message = "La empresa no puede ser nula")
    private Empresa empresaid;

    public Encargados(Integer id) {
        this.id = id;
    }

    public Encargados() {

    }
}
