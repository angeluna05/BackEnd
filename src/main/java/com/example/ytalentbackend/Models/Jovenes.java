package com.example.ytalentbackend.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "jovenes")
public class Jovenes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jovenid", nullable = false)
    private Integer id;

    @NotNull(message = "El documento no puede ser nulo")
    @Min(value = 99999, message = "El documento debe tener al menos 6 dígitos.")
    @Column(name = "documento", nullable = false)
    private Integer documento;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 50, message = "El nombre no puede exceder los 50 caracteres")
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacío")
    @Size(max = 50, message = "El apellido no puede exceder los 50 caracteres")
    @Column(name = "apellido", nullable = false, length = 50)
    private String apellido;

    @NotNull(message = "La fecha de nacimiento no puede ser nula")
    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @NotBlank(message = "El número de contacto no puede estar vacío")
    @Size(max = 15, message = "El número de contacto no puede exceder los 15 caracteres")
    @Column(name = "numero_contacto", nullable = false, length = 15)
    private String numeroContacto;

    @NotBlank(message = "El correo no puede estar vacío")
    @Email(message = "El correo debe tener un formato válido")
    @Size(max = 50, message = "El correo no puede exceder los 50 caracteres")
    @Column(name = "correo", nullable = false, length = 50)
    private String correo;

    @NotBlank(message = "Tipo de institución no puede estar vacío")
    @Size(max = 50, message = "Tipo de institución no puede exceder los 50 caracteres")
    @Column(name = "tipo_institucion", nullable = false, length = 50)
    private String tipo_institucion;

    @NotBlank(message = "La institución no puede estar vacío")
    @Size(max = 50, message = "La institución no puede exceder los 50 caracteres")
    @Column(name = "institucion", nullable = false, length = 50)
    private String institucion;

    @NotBlank(message = "Las habilidades no pueden estar vacías")
    @Size(max = 500, message = "Las habilidades no pueden exceder los 500 caracteres")
    @Column(name = "habilidades", nullable = false, length = 500)
    private String habilidades;

    @NotBlank(message = "Las características no pueden estar vacías")
    @Size(max = 500, message = "Las características no pueden exceder los 500 caracteres")
    @Column(name = "caracteristicas", nullable = false, length = 500)
    private String caracteristicas;

    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(max = 1000, message = "La descripción no puede exceder los 1000 caracteres")
    @Column(name = "descripcion", nullable = false, length = 1000)
    private String descripcion;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "tipo_documentoid", nullable = false)
    @NotNull(message = "El tipo de documento no puede ser nulo")
    private Tipodocumento tipoDocumentoid;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "estadoid", nullable = false)
    @NotNull(message = "El estado no puede ser nulo")
    private Estado estadoid;

    public Jovenes(Integer id) {
        this.id = id;
    }

    public Jovenes() {

    }

}
