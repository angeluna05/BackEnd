package com.example.ytalentbackend.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "retosempresa")
public class Retosempresas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "retosempresaid", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "retoid", nullable = false)
    private Retos retoid;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "empresaid", nullable = false)
    private Empresa empresaid;

    public Retosempresas(Integer id) {
        this.id = id;
    }

    public Retosempresas() {

    }
}