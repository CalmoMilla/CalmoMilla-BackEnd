package com.calmomilla.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private  String tituloPostagem;

    @Column(nullable = false)
    @Lob
    private String descPostagem;

    @Column(nullable = false)
    private String foto;

    @Column(nullable = false)
    private String revisao;

    @ManyToOne
    @Column(nullable = false)
    private Psicologo autor;


}
