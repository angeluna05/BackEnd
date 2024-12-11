package com.example.ytalentbackend.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "empresa")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empresaid", nullable = false)
    private Integer id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 50, message = "El nombre no puede exceder los 50 caracteres")
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @NotBlank(message = "El correo no puede estar vacío")
    @Email(message = "El correo debe tener un formato válido")
    @Size(max = 50, message = "El correo no puede exceder los 50 caracteres")
    @Column(name = "correo", nullable = false, length = 50)
    private String correo;

    @NotBlank(message = "El número de contacto no puede estar vacío")
    @Size(max = 10, message = "El número de contacto no puede exceder los 10 caracteres")
    @Column(name = "numero_contacto", nullable = false, length = 20)
    private String numero_contacto;

    @NotBlank(message = "El departamento no puede estar vacío")
    @Size(max = 50, message = "El departamento no puede exceder los 50 caracteres")
    @Column(name = "departamento", nullable = false, length = 50)
    private String departamento;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "estadoid", nullable = false)
    @NotNull(message = "El estado no puede ser nulo")
    private Estado estadoid;

    public Empresa(Integer id) {
        this.id = id;
    }

    public Empresa() {

    }
}
