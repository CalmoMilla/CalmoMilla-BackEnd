package com.calmomilla.domain.service;

import com.calmomilla.api.dto.input.jogo.AtualizarJogoInput;
import com.calmomilla.api.dto.output.jogoOutput.JogoOutput;
import com.calmomilla.domain.exception.DataNotFoundException;
import com.calmomilla.domain.model.Jogo;
import com.calmomilla.domain.repository.JogoRepository;
import com.calmomilla.domain.utils.ModelMapperUtils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class JogoService {

    private final ModelMapperUtils mapperUtils;
    private final ModelMapper modelMapper;
    private final JogoRepository jogoRepository;
    public List<JogoOutput>buscarTodos(){
        List<Jogo> jogos = jogoRepository.findAll();
        return mapperUtils.mapList(jogos,JogoOutput.class);
    }

    public ResponseEntity<JogoOutput>buscarPorId(String id){

        Optional<Jogo> jogo = jogoRepository.findById(id);

        if (jogo.isEmpty()){
            throw new DataNotFoundException("Jogo não encontrado");
        }

        JogoOutput jogoOutput = modelMapper.map(jogo.get(),JogoOutput.class);

        return ResponseEntity.ok(jogoOutput);
    }

    public ResponseEntity<JogoOutput>atualizar(AtualizarJogoInput atualizarJogoInput){

        JogoOutput jogoOutput = buscarPorId(atualizarJogoInput.getId()).getBody();
        Jogo jogo = modelMapper.map(jogoOutput,Jogo.class);

        jogo = modelMapper.map(atualizarJogoInput,Jogo.class);
        jogo = jogoRepository.save(jogo);

        JogoOutput jogoOutput1 = modelMapper.map(jogo,JogoOutput.class);
        return ResponseEntity.ok(jogoOutput1);
    }

}
