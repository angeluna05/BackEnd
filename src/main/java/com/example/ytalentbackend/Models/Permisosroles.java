package com.example.ytalentbackend.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Entity
@Data
@AllArgsConstructor
@Builder
@Table(name = "permisosroles")
public class Permisosroles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permisos_rolesid", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "rolid", nullable = false)
    private Roles rolid;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "permisosid", nullable = false)
    private Permisos permisosid;

    public Permisosroles(Integer id) {
        this.id = id;
    }
    public Permisosroles() {

    }
}