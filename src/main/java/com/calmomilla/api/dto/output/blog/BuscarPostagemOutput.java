package com.calmomilla.api.dto.output.blog;

import com.calmomilla.domain.model.Psicologo;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class BuscarPostagemOutput {

    private  String tituloPostagem;

    private String descPostagem;

    private String foto;

    private String revisao;

    private Psicologo autor;

}
