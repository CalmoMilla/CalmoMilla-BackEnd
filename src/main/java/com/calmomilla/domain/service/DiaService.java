package com.calmomilla.domain.service;

import com.calmomilla.api.dto.input.dia.AtualizarDiaInput;
import com.calmomilla.api.dto.input.dia.CadastroDiaInput;
import com.calmomilla.api.dto.output.dia.AtualizarDiaOutput;
import com.calmomilla.api.dto.output.dia.BuscarDiaOutput;
import com.calmomilla.api.dto.output.dia.CadastroDiaOutput;
import com.calmomilla.domain.model.Dia;
import com.calmomilla.domain.repository.DiaRepository;
import com.calmomilla.domain.utils.ModelMapperUtils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public ResponseEntity<Void> deletar(String id) throws NoSuchMethodException {
        if (diaRepository.findById(id).isEmpty()) {
            throw new NoSuchMethodException("id não encontrado");
        }
        diaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<BuscarDiaOutput> buscarPorId (String id) throws NoSuchMethodException {
        if (diaRepository.findById(id).isEmpty()) {
            throw new NoSuchMethodException("id não encontrado");
        }
        Dia dia = diaRepository.findById(id).get();
        BuscarDiaOutput buscarDiaOutput = modelMapper.map(dia, BuscarDiaOutput.class);
        return ResponseEntity.ok(buscarDiaOutput);
    }

    public List<BuscarDiaOutput> buscarTodos() {
        List<Dia> dias = diaRepository.findAll();
        List<BuscarDiaOutput> buscarDias = modelMapperUtils.mapList(dias, BuscarDiaOutput.class);
        return buscarDias;
    }

    public ResponseEntity<AtualizarDiaOutput> atualizar
            (AtualizarDiaInput atualizarDia) throws NoSuchMethodException {
       BuscarDiaOutput buscarDiaOutput = buscarPorId(atualizarDia.getId()).getBody();
       Dia dia = modelMapper.map(buscarDiaOutput, Dia.class);
       dia = modelMapper.map(atualizarDia, Dia.class);
       Dia diaSalvo = diaRepository.save(dia);
       AtualizarDiaOutput atualizarDiaOutput = modelMapper.map(diaSalvo, AtualizarDiaOutput.class);
       return ResponseEntity.ok(atualizarDiaOutput);
    }
}
