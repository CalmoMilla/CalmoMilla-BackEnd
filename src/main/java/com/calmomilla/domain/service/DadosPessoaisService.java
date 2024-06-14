package com.calmomilla.domain.service;

import com.calmomilla.api.dto.input.dadosPessoais.AtualizarDadosPessoaisinput;
import com.calmomilla.api.dto.input.dadosPessoais.CadastroDadosPessoaisInput;
import com.calmomilla.api.dto.input.paciente.AtualizarPacienteInput;
import com.calmomilla.api.dto.output.dadosPessoais.AtualizarDadosPessoaisOutput;
import com.calmomilla.api.dto.output.dadosPessoais.CadastroDadosPessoaisOutput;
import com.calmomilla.api.dto.output.dadosPessoais.BuscarDadosPessoaisOutput;
import com.calmomilla.api.dto.output.paciente.AtualizarPacienteOutput;
import com.calmomilla.api.dto.output.paciente.BuscarPacienteOutput;
import com.calmomilla.domain.exception.DataNotFoundException;
import com.calmomilla.domain.model.DadosPessoais;
import com.calmomilla.domain.model.Paciente;
import com.calmomilla.domain.repository.DadosPessoaisRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DadosPessoaisService {

    private final ModelMapper modelMapper;
    private final DadosPessoaisRepository dadosPessoaisRepository;
    private final PacienteService pacienteService;
    public ResponseEntity<BuscarDadosPessoaisOutput> buscarPorId(String id){

        Optional<DadosPessoais> dadosPessoais = dadosPessoaisRepository.findById(id);

        if (dadosPessoais.isEmpty()){
            throw new DataNotFoundException("Dados pessoais não encontrado");
        }

        BuscarDadosPessoaisOutput dadosPessoaisOutput = modelMapper.map(dadosPessoais.get(),BuscarDadosPessoaisOutput.class);

        return ResponseEntity.ok(dadosPessoaisOutput);

    }


    public ResponseEntity<CadastroDadosPessoaisOutput> cadastrar(CadastroDadosPessoaisInput dadosPessoaisInput) throws NoSuchMethodException {

        DadosPessoais dadosPessoais = modelMapper.map(dadosPessoaisInput,DadosPessoais.class);



      dadosPessoais = dadosPessoaisRepository.save(dadosPessoais);

      BuscarPacienteOutput pacienteOutput = pacienteService.buscarPorId(dadosPessoais.getPaciente().getId()).getBody();
      AtualizarPacienteInput pacienteInput = modelMapper.map(pacienteOutput,AtualizarPacienteInput.class);
      pacienteInput.setDadosPessoais(dadosPessoais);
      pacienteService.atualizar(pacienteInput);
      CadastroDadosPessoaisOutput dadosPessoaisOutput = modelMapper.map(dadosPessoais, CadastroDadosPessoaisOutput.class);

      return ResponseEntity.ok(dadosPessoaisOutput);

    }

    public ResponseEntity<AtualizarDadosPessoaisOutput> atualizar(AtualizarDadosPessoaisinput dadosPessoaisInput){

       BuscarDadosPessoaisOutput dadosPessoaisOutput = buscarPorId(dadosPessoaisInput.getId()).getBody();

       DadosPessoais dadosPessoais = modelMapper.map(dadosPessoaisOutput,DadosPessoais.class);

       dadosPessoais = modelMapper.map(dadosPessoaisInput,DadosPessoais.class);

      dadosPessoais =  dadosPessoaisRepository.save(dadosPessoais);

      AtualizarDadosPessoaisOutput atualizarDadosPessoaisOutput = modelMapper.map(dadosPessoais, AtualizarDadosPessoaisOutput.class);

      return ResponseEntity.ok(atualizarDadosPessoaisOutput);

    }

    public ResponseEntity<?> deletar(String id) throws NoSuchMethodException {

        BuscarDadosPessoaisOutput dadosPessoaisOutput = buscarPorId(id).getBody();

        assert dadosPessoaisOutput != null;

        BuscarPacienteOutput paciente = pacienteService.buscarPorId(dadosPessoaisOutput.getPaciente().getId()).getBody();
        assert paciente != null;
        paciente.setDadosPessoais(null);
        AtualizarPacienteInput pacienteInput = modelMapper.map(paciente,AtualizarPacienteInput.class);
        pacienteService.atualizar(pacienteInput);
        dadosPessoaisRepository.deleteById(dadosPessoaisOutput.getId());
        return ResponseEntity.noContent().build();

    }

}
