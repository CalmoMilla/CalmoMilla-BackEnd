package com.calmomilla.domain.model;

import com.calmomilla.domain.utils.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank
    private String nome;

    private String genero;

    @Column(unique = true)
    private String cpf;

   private LocalDate dataNasc;

   @OneToOne(optional = true)
   private Endereco endereco;

    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    private String senha;

    @Column(unique = true)
    private String telefone;

    private String foto;

    @NotNull
    private UserRole role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.ADMIN){
            return  List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));

        } else if (this.role == UserRole.PSICOLOGO) {
            return List.of(new SimpleGrantedAuthority("ROLE_PSICOLOGO"));

        }else {
        return List.of(new SimpleGrantedAuthority("ROLE_PACIENTE"));
    }
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
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
}
