package com.example.ytalentbackend.Models;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Builder
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name="usuarios", uniqueConstraints = {@UniqueConstraint(columnNames = {"correo"})})
public class usuarios implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuarioid", nullable = false)
    private Integer id;

    @NotBlank(message = "El correo no puede estar vacío")
    @Email(message = "El correo debe ser una dirección de correo electrónico válida")
    @Size(max = 200, message = "El correo no puede exceder los 200 caracteres")
    @Column(name = "correo", nullable = false, length = 200)
    private String correo;

    @NotBlank(message = "El nombre no puede ser nulo")
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(max = 500, message = "La contraseña no puede exceder los 500 caracteres")
    @Column(name = "contrasena", nullable = false, length = 500)
    private String contrasena;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "estadoid", nullable = false)
    @NotNull(message = "El estado no puede ser nulo")
    private Estado estadoid;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "rolid", nullable = false)
    @NotNull(message = "El rol no puede ser nulo")
    private Roles rolid;

//     @Override
// public Collection<? extends GrantedAuthority> getAuthorities() {
//     return List.of(new SimpleGrantedAuthority(rolid.getNombre()));
// }
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return rolid.getPermisos().stream()
//                .map(permiso -> new SimpleGrantedAuthority(permiso.getNombre()))
//                .collect(Collectors.toList());
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return this.contrasena;
    }

    @Override
    public String getUsername() {
        return this.correo;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public usuarios(Integer id) {
        this.id = id;
    }
    public usuarios() {

    }
}
