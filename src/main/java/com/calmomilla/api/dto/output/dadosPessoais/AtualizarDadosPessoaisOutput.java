package com.calmomilla.api.dto.output.dadosPessoais;

import com.calmomilla.domain.model.Paciente;
import com.calmomilla.domain.utils.enums.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AtualizarDadosPessoaisOutput {

    private String id;
    private EstadoCivil estadoCivil;
    private String condicoesMedicas;
    private String doencasMentais;
    private String medicamentos;
    private String alergias;
    private String doencasMentaisFamilia;
    private String doencasNeurodegenerativasFamilia;
    private String terapia;
    private String hospitalizacao;
    private SimNao pensamentosSuicidas;
    private String detalhesPensamentosSuicidas;
    private Alimentacao alimentacao;
    private String especificarAlimentacao;
    private FrequenciaExercicios frequenciaExercicios;
    private QualidadeSono qualidadeSono;
    private FrequenciaFumo fumante;
    private FrequenciaAlcool frequenciaAlcool;
    private FrequenciaEstresse frequenciaEstresse;
    private String fatoresEstresse;
    private String lidarEstresse;
    private RelacionamentoFamiliar relacionamentoFamiliar;
    private String redeApoio;
    private RelacionamentoAtual relacionamentoAtual;
    private String objetivos;
    private String mudancasEsperadas;
    private String areaFoco;
    private Frequencia dificuldadeInformacoesRecentes;
    private Frequencia esquecerEventosImportantes;
    private Paciente paciente;
}
