package com.example.ytalentbackend.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "jovenesequipos")
public class Jovenesequipos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jovenes_equiposid", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "jovenesid", nullable = false)
    private Jovenes jovenesid;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "equiposid", nullable = false)
    private Equipos equiposid;

    public Jovenesequipos(Integer id) {
        this.id = id;
    }

    public Jovenesequipos() {

    }
}