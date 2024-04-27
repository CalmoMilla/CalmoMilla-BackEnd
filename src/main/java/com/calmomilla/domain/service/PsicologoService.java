package com.calmomilla.domain.service;

import com.calmomilla.api.dto.input.AtualizarPsicologoInput;
import com.calmomilla.api.dto.output.AtualizarPsicologoOutput;
import com.calmomilla.api.dto.output.BuscarPsicologoOutput;
import com.calmomilla.domain.model.Psicologo;
import com.calmomilla.domain.repository.PsicologoRepository;

import com.calmomilla.domain.utils.ModelMapperUtils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PsicologoService {

    private final PsicologoRepository psicologoRepository;
    private final ModelMapper modelMapper;
    private final ModelMapperUtils mapperUtils;
    public List<BuscarPsicologoOutput> buscarTodos(){

        List<Psicologo> psicologos = psicologoRepository.findAll();
        List<BuscarPsicologoOutput> psicologoOutputs = mapperUtils.mapList(psicologos, BuscarPsicologoOutput.class);
        return psicologoOutputs;

    }

    public ResponseEntity<BuscarPsicologoOutput> buscarPorId(String id){
        if (psicologoRepository.findById(id).isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        Psicologo psicologo = psicologoRepository.findById(id).get();

        BuscarPsicologoOutput psicologoOutput = modelMapper.map(psicologo, BuscarPsicologoOutput.class);

        return ResponseEntity.ok(psicologoOutput);

    }

    public ResponseEntity<AtualizarPsicologoOutput> atualizar(AtualizarPsicologoInput psicologoInput) {
        if (psicologoRepository.findById(psicologoInput.getId()).isEmpty()){
            return ResponseEntity.noContent().build();
        }
       Psicologo psicologo = psicologoRepository.findById(psicologoInput.getId()).get();
        psicologo = modelMapper.map(psicologoInput,Psicologo.class);
        psicologoRepository.save(psicologo);
        AtualizarPsicologoOutput psicologoOutput = modelMapper.map(psicologo, AtualizarPsicologoOutput.class);
        return ResponseEntity.ok(psicologoOutput);


    }

    public ResponseEntity<Void>deletar(String id){

        if (psicologoRepository.findById(id).isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        psicologoRepository.deleteById(id);
       return ResponseEntity.noContent().build();
    }
}

