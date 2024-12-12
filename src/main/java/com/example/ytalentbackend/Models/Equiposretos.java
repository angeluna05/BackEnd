package com.example.ytalentbackend.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "equiposretos")
public class Equiposretos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "equiposretosid", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "equiposid", nullable = false)
    private Equipos equiposid;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "retosid", nullable = false)
    private Retos retosid;


    public Equiposretos(Integer id) {
        this.id = id;
    }

    public Equiposretos() {
    }
}