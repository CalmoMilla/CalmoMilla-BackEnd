package com.calmomilla.domain.service;

import com.calmomilla.api.dto.input.desempenho.AdicionarDesempenhoInput;
import com.calmomilla.api.dto.input.jogo.AtualizarJogoInput;
import com.calmomilla.api.dto.input.paciente.AtualizarPacienteInput;
import com.calmomilla.api.dto.output.desempenho.BuscarDesempenhoOutput;
import com.calmomilla.api.dto.output.desempenho.AdicionarDesempenhoOutput;
import com.calmomilla.api.dto.output.desempenho.BuscarDesempenhoUsuarioOutput;
import com.calmomilla.api.dto.output.jogoOutput.JogoOutput;
import com.calmomilla.api.dto.output.paciente.BuscarPacienteOutput;
import com.calmomilla.domain.exception.DataNotFoundException;
import com.calmomilla.domain.model.Desempenho;
import com.calmomilla.domain.model.Paciente;
import com.calmomilla.domain.repository.DesempenhoRepository;
import com.calmomilla.domain.utils.ModelMapperUtils;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DesempenhoService {

    private final PacienteService pacienteService;
    private final JogoService jogoService;
    private final DesempenhoRepository desempenhoRepository;
    private final ModelMapper modelMapper;
    private final ModelMapperUtils mapperUtils;

    public ResponseEntity<BuscarDesempenhoOutput>buscarPorId(String id){

        Optional<Desempenho> desempenho = desempenhoRepository.findById(id);

        if (desempenho.isEmpty()){
            throw new DataNotFoundException("Dados de desempenho não encontrados");
        }

        BuscarDesempenhoOutput desempenhoOutput = modelMapper.map(desempenho.get(), BuscarDesempenhoOutput.class);
        return ResponseEntity.ok(desempenhoOutput);
    }

    public ResponseEntity<List<BuscarDesempenhoUsuarioOutput>>buscarPorIdUsuario(String id) throws NoSuchMethodException {

        BuscarPacienteOutput pacienteOutput = pacienteService.buscarPorId(id).getBody();
        Paciente paciente = modelMapper.map(pacienteOutput,Paciente.class);
        List<Desempenho> desempenhos = desempenhoRepository.findByUsuario(paciente);
        List<BuscarDesempenhoUsuarioOutput> desempenhoOutput = mapperUtils.mapList(desempenhos, BuscarDesempenhoUsuarioOutput.class);
        return ResponseEntity.ok(desempenhoOutput);
    }

    @Transactional
    public ResponseEntity<AdicionarDesempenhoOutput>adicionar(AdicionarDesempenhoInput desempenhoinput) throws NoSuchMethodException {

        Desempenho desempenho = modelMapper.map(desempenhoinput, Desempenho.class);

        desempenho = desempenhoRepository.save(desempenho);

        AdicionarDesempenhoOutput desempenhoOutput = modelMapper.map(desempenho, AdicionarDesempenhoOutput.class);
       BuscarPacienteOutput pacienteOutput =  pacienteService.buscarPorId(desempenho.getUsuario().getId()).getBody();
       AtualizarPacienteInput paciente = modelMapper.map(pacienteOutput, AtualizarPacienteInput.class);

       List<Desempenho> desempenhos = new ArrayList<>();

        if (!paciente.getDesempenhos().isEmpty()){
            desempenhos.add((Desempenho) paciente.getDesempenhos());
       }

       paciente.setDesempenhos(desempenhos);
       pacienteService.atualizar(paciente);

        JogoOutput jogoOutput = jogoService.buscarPorId(desempenho.getJogos().getId()).getBody();
        AtualizarJogoInput jogoInput = modelMapper.map(jogoOutput,AtualizarJogoInput.class);
        List<Desempenho> desempenhosJogos = new ArrayList<>();
        if (!jogoInput.getDesempenhos().isEmpty()){
            desempenhosJogos.add((Desempenho)  jogoInput.getDesempenhos());
        }

        desempenhosJogos.add(desempenho);
        jogoInput.setDesempenhos(desempenhosJogos);
        jogoService.atualizar(jogoInput);

        return ResponseEntity.ok(desempenhoOutput);

    }



}
