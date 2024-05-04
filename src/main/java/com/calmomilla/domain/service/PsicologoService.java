package com.calmomilla.domain.service;


import com.calmomilla.api.dto.input.psicologo.AtualizarPsicologoInput;
import com.calmomilla.api.dto.input.psicologo.CadastroPsicologoInput;
import com.calmomilla.api.dto.output.psicologo.AtualizarPsicologoOutput;
import com.calmomilla.api.dto.output.psicologo.BuscarPsicologoOutput;
import com.calmomilla.api.dto.output.psicologo.CadastroPsicologoOutput;
import com.calmomilla.domain.exception.NegocioException;
import com.calmomilla.domain.model.Psicologo;
import com.calmomilla.domain.repository.PsicologoRepository;

import com.calmomilla.domain.utils.ModelMapperUtils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public ResponseEntity<BuscarPsicologoOutput> buscarPorId(String id) throws NoSuchMethodException {
        if (psicologoRepository.findById(id).isEmpty()) {
           throw new NoSuchMethodException("id não encontrado");
        }

        Psicologo psicologo = psicologoRepository.findById(id).get();

        BuscarPsicologoOutput psicologoOutput = modelMapper.map(psicologo, BuscarPsicologoOutput.class);

        return ResponseEntity.ok(psicologoOutput);

    }

    public ResponseEntity<CadastroPsicologoOutput> cadastrar(CadastroPsicologoInput psicologoInput) {

        if (psicologoRepository.findByEmail(psicologoInput.getEmail()).isPresent()) {
            throw new NegocioException("esse email ja está em uso");
        }
        var senhaCriptografada = new BCryptPasswordEncoder().encode(psicologoInput.getSenha());
        psicologoInput.setSenha(senhaCriptografada);
        Psicologo psicologo = modelMapper.map(psicologoInput, Psicologo.class);
        Psicologo psicologoCadastrado = psicologoRepository.save(psicologo);

        CadastroPsicologoOutput psicologoOutput = modelMapper.map(psicologoCadastrado, CadastroPsicologoOutput.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(psicologoOutput);

    }

    public ResponseEntity<AtualizarPsicologoOutput> atualizar(AtualizarPsicologoInput psicologoInput) throws NoSuchMethodException {

        BuscarPsicologoOutput buscarPsicologoOutput = buscarPorId(psicologoInput.getId()).getBody();

        Psicologo psicologo = modelMapper.map(buscarPsicologoOutput,Psicologo.class);

        if (!psicologo.getSenha().equals(psicologoInput.getSenha())){
            String senhaCriptografada = new BCryptPasswordEncoder().encode(psicologoInput.getSenha());
            psicologoInput.setSenha(senhaCriptografada);
        }

        psicologo = modelMapper.map(psicologoInput,Psicologo.class);
        Psicologo psicologoSalvo = psicologoRepository.save(psicologo);
        AtualizarPsicologoOutput psicologoOutput = modelMapper.map(psicologoSalvo, AtualizarPsicologoOutput.class);
        return ResponseEntity.ok(psicologoOutput);


    }

    public ResponseEntity<Void>deletar(String id) throws NoSuchMethodException {

        if (psicologoRepository.findById(id).isEmpty()){
            throw new NoSuchMethodException("id não encontrado");
        }
        psicologoRepository.deleteById(id);
       return ResponseEntity.noContent().build();
    }
}

