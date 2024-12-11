package com.example.ytalentbackend.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Data
@AllArgsConstructor
@Builder
@Table(name = "roles")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rolid", nullable = false)
    private Integer id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 200, message = "El nombre no puede exceder los 200 caracteres")
    @Column(name = "nombre", nullable = false, length = 200)
    private String nombre;

    @NotNull(message = "El estado no puede ser nulo")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "estadoid", nullable = false)
    private Estado estadoid;

    public Roles(Integer id) {
        this.id = id;
    }
    public Roles() {

    }
}
