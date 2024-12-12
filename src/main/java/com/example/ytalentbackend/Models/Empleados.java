package com.example.ytalentbackend.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "empleados")
public class Empleados {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empleadoid", nullable = false)
    private Integer id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 50, message = "El nombre no puede exceder los 50 caracteres")
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacío")
    @Size(max = 50, message = "El apellido no puede exceder los 50 caracteres")
    @Column(name = "apellido", nullable = false, length = 50)
    private String apellido;

    @NotNull(message = "El documento no puede estar vacío")
    @Min(value = 99999, message = "El documento debe tener al menos 6 dígitos.")
    @Column(name = "documento", nullable = false)
    private Integer documento;

    @Past(message = "La fecha de nacimiento debe ser anterior a la fecha actual")
    @NotNull(message = "La fecha de nacimiento no puede ser nula")
    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @NotBlank(message = "El número de contacto no puede estar vacío")
    @Size(max = 10, message = "El número de contacto no puede exceder los 10 caracteres")
    @Column(name = "numero_contacto", nullable = false, length = 20)
    private String numeroContacto;

    @NotBlank(message = "El cargo no puede estar vacío")
    @Size(max = 50, message = "El cargo no puede exceder los 50 caracteres")
    @Column(name = "cargo", nullable = false, length = 50)
    private String cargo;

    @NotBlank(message = "El correo no puede estar vacío")
    @Email(message = "El correo debe tener un formato válido")
    @Size(max = 50, message = "El correo no puede exceder los 50 caracteres")
    @Column(name = "correo", nullable = false, length = 50)
    private String correo;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "tipo_documentoid", nullable = false)
    @NotNull(message = "El tipo de documento no puede ser nulo")
    private Tipodocumento tipoDocumentoid;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "estadoid", nullable = false)
    @NotNull(message = "El estado no puede ser nulo")
    private Estado estadoid;

    public Empleados(Integer id) {
        this.id = id;
    }

    public Empleados() {

    }
}
