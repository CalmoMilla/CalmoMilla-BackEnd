package com.calmomilla.domain.model;

import com.calmomilla.domain.utils.enums.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DadosPessoais {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    @Enumerated(EnumType.STRING)
    private EstadoCivil estadoCivil;

    @Lob
    private String condicoesMedicas;

    @Lob
    private String doencasMentais;

    @Lob
    private String medicamentos;

    @Lob
    private String alergias;

    @Lob
    private String doencasMentaisFamilia;

    @Lob
    private String doencasNeurodegenerativasFamilia;

    @Lob
    private String terapia;

    @Lob
    private String hospitalizacao;

    @Enumerated(EnumType.STRING)
    private SimNao pensamentosSuicidas;

    @Lob
    private String detalhesPensamentosSuicidas;

    @Enumerated(EnumType.STRING)
    private Alimentacao alimentacao;

    private String especificarAlimentacao;

    @Enumerated(EnumType.STRING)
    private FrequenciaExercicios frequenciaExercicios;

    @Enumerated(EnumType.STRING)
    private QualidadeSono qualidadeSono;

    @Enumerated(EnumType.STRING)
    private FrequenciaFumo fumante;

    @Enumerated(EnumType.STRING)
    private FrequenciaAlcool frequenciaAlcool;

    @Enumerated(EnumType.STRING)
    private FrequenciaEstresse frequenciaEstresse;

    @Lob
    private String fatoresEstresse;

    @Lob
    private String lidarEstresse;

    @Enumerated(EnumType.STRING)
    private RelacionamentoFamiliar relacionamentoFamiliar;

    @Lob
    private String redeApoio;

    @Enumerated(EnumType.STRING)
    private RelacionamentoAtual relacionamentoAtual;

    @Lob
    private String objetivos;

    @Lob
    private String mudancasEsperadas;

    @Lob
    private String areaFoco;

    @Enumerated(EnumType.STRING)
    private Frequencia dificuldadeInformacoesRecentes;

    @Enumerated(EnumType.STRING)
    private Frequencia esquecerEventosImportantes;

}

