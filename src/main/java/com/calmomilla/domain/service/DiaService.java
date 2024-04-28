package com.calmomilla.domain.service;

import com.calmomilla.api.dto.input.CadastroDiaInput;
import com.calmomilla.api.dto.input.CadastroTarefaInput;
import com.calmomilla.api.dto.output.CadastroDiaOutput;
import com.calmomilla.domain.model.Dia;
import com.calmomilla.domain.repository.DiaRepository;
import com.calmomilla.domain.utils.ModelMapperUtils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DiaService {

    private final DiaRepository diaRepository;
    private final ModelMapper modelMapper;
    private final ModelMapperUtils modelMapperUtils;

    public ResponseEntity<CadastroDiaOutput> cadastrarDia(CadastroDiaInput cadastroDiaInput) {
        Dia dia = modelMapper.map(cadastroDiaInput, Dia.class);
        Dia diaSalvo = diaRepository.save(dia);
        CadastroDiaOutput cadastroDiaOutput = modelMapper.map(diaSalvo, CadastroDiaOutput.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(cadastroDiaOutput);
    }
}
