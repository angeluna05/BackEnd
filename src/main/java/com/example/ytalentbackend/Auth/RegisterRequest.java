package com.example.ytalentbackend.Auth;

import com.example.ytalentbackend.Models.Tipodocumento;
import com.example.ytalentbackend.Models.Estado;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Date;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    
    @NotBlank(message = "El correo no puede estar vacío")
    @Email(message = "El correo debe ser una dirección de correo electrónico válida")
    @Size(max = 200, message = "El correo no puede exceder los 200 caracteres")
    @Column(name = "correo", nullable = false, length = 200)
    private String correo;
    
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

    @NotNull(message = "La fecha de nacimiento no puede estar vacía")
    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @NotBlank(message = "El número de contacto no puede estar vacío")
    @Size(max = 15, message = "El número de contacto no puede exceder los 15 caracteres")
    @Column(name = "numero_contacto", nullable = false, length = 15)
    private String numeroContacto;

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

    @NotNull(message = "El tipo de documento no puede estar vacío")
    private Tipodocumento tipoDocumentoid;

    @NotNull(message = "El estado no puede estar vacío")
    private Integer estadoid;

    @NotNull(message = "El rol no puede estar vacío")
    private Integer rolid;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(max = 500, message = "La contraseña no puede exceder los 500 caracteres")
    @Column(name = "contrasena", nullable = false, length = 500)
    private String contrasena;
}
