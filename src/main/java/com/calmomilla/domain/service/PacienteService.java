package com.calmomilla.domain.service;
import com.calmomilla.api.dto.input.paciente.AtualizarPacienteInput;
import com.calmomilla.api.dto.input.paciente.CadastroPacienteInput;
import com.calmomilla.api.dto.output.paciente.AtualizarPacienteOutput;
import com.calmomilla.api.dto.output.paciente.BuscarPacienteOutput;
import com.calmomilla.api.dto.output.paciente.CadastroPacienteOutput;
import com.calmomilla.domain.exception.NegocioException;
import com.calmomilla.domain.model.Paciente;

import com.calmomilla.domain.model.Psicologo;
import com.calmomilla.domain.repository.PacienteRepository;
import com.calmomilla.domain.utils.ModelMapperUtils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final ModelMapper modelMapper;
    private final ModelMapperUtils mapperUtils;
    public List<BuscarPacienteOutput> buscarTodos(){
        List<Paciente> pacientes = pacienteRepository.findAll();
        List<BuscarPacienteOutput> pacienteOutputs = mapperUtils.mapList(pacientes, BuscarPacienteOutput.class);
       return pacienteOutputs;
    }

    public ResponseEntity<BuscarPacienteOutput> buscarPorId(String id) throws NoSuchMethodException {
        Optional<Paciente> paciente = pacienteRepository.findById(id);

        if (paciente.isEmpty()) {
            throw new NoSuchMethodException("id não encontrado");
        }
        BuscarPacienteOutput pacienteOutput = modelMapper.map(paciente.get(), BuscarPacienteOutput.class);
        return ResponseEntity.ok(pacienteOutput);
    }

    public ResponseEntity<CadastroPacienteOutput> cadastrar(CadastroPacienteInput pacienteInput) {

        if (pacienteRepository.findByEmail(pacienteInput.getEmail()) != null) {
            throw new NegocioException("esse email ja está em uso");
        }

        String senhaCriptografada = new BCryptPasswordEncoder().encode(pacienteInput.getSenha());
        pacienteInput.setSenha(senhaCriptografada);
        Paciente paciente = modelMapper.map(pacienteInput,Paciente.class);
        paciente = pacienteRepository.save(paciente);
        CadastroPacienteOutput pacienteOutput= modelMapper.map(paciente,CadastroPacienteOutput.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteOutput);
    }
    public ResponseEntity<AtualizarPacienteOutput>atualizar(AtualizarPacienteInput pacienteInput) throws NoSuchMethodException {

        BuscarPacienteOutput pacienteOutput = buscarPorId(pacienteInput.getId()).getBody();

        Paciente paciente = modelMapper.map(pacienteOutput, Paciente.class);

        if (!paciente.getSenha().equals(pacienteInput.getSenha())){
            String senhaCriptografada = new BCryptPasswordEncoder().encode(paciente.getSenha());
            pacienteInput.setSenha(senhaCriptografada);
        }
        paciente = modelMapper.map(pacienteInput, Paciente.class);
        paciente = pacienteRepository.save(paciente);
        AtualizarPacienteOutput atualizarPacienteOutput = modelMapper.map(paciente,AtualizarPacienteOutput.class);
        return ResponseEntity.ok(atualizarPacienteOutput);
    }

    public ResponseEntity<Void>deletar(String id) throws NoSuchMethodException {
        BuscarPacienteOutput pacienteOutput = buscarPorId(id).getBody();

        assert pacienteOutput != null;
        pacienteRepository.deleteById(pacienteOutput.getId());
        return ResponseEntity.noContent().build();
    }

}
