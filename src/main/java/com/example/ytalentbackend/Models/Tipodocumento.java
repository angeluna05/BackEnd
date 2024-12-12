package com.example.ytalentbackend.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tipodocumento")
public class Tipodocumento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tipo_documentoid", nullable = false)
    private Integer id;

    @NotBlank(message = "Las siglas no pueden estar vacías")
    @Size(max = 5, message = "Las siglas no pueden exceder los 5 caracteres")
    @Column(name = "siglas", nullable = false, length = 5)
    private String siglas;

    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(max = 50, message = "La descripción no puede exceder los 50 caracteres")
    @Column(name = "descripcion", nullable = false, length = 50)
    private String descripcion;

    // Constructor que acepta un Integer como argumento
    public Tipodocumento(Integer id) {
        this.id = id;
    }

    // Constructor vacío (requerido por JPA)
    public Tipodocumento() {
    }

}
