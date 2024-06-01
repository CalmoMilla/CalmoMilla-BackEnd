package com.calmomilla.domain.service;
import com.calmomilla.api.dto.input.paciente.AtualizarPacienteInput;
import com.calmomilla.api.dto.input.paciente.CadastroPacienteInput;
import com.calmomilla.api.dto.output.paciente.AtualizarPacienteOutput;
import com.calmomilla.api.dto.output.paciente.BuscarPacienteEmailOutput;
import com.calmomilla.api.dto.output.paciente.BuscarPacienteOutput;
import com.calmomilla.api.dto.output.paciente.CadastroPacienteOutput;
import com.calmomilla.domain.exception.NegocioException;
import com.calmomilla.domain.model.Paciente;

import com.calmomilla.domain.repository.PacienteRepository;
import com.calmomilla.domain.utils.ModelMapperUtils;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final ModelMapper modelMapper;
    private final ModelMapperUtils mapperUtils;
    private EmailService emailService;

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
    public ResponseEntity<BuscarPacienteEmailOutput> buscarInfo(Principal principal) throws NoSuchMethodException {
        Optional<Paciente> paciente = pacienteRepository.findByEmail(principal.getName());

        if (paciente.isEmpty()) {
            throw new NoSuchMethodException("Email não encontrado");
        }
        BuscarPacienteEmailOutput pacienteOutput = modelMapper.map(paciente.get(), BuscarPacienteEmailOutput.class);
        return ResponseEntity.ok(pacienteOutput);
    }

    @Transactional
    public ResponseEntity<CadastroPacienteOutput> cadastrar(CadastroPacienteInput pacienteInput) {

        if (pacienteRepository.existsByCpfOrEmailOrTelefone(pacienteInput.getCpf(), pacienteInput.getEmail(), pacienteInput.getTelefone())) {
            throw new DataIntegrityViolationException("Recurso está em uso");
        }

        String senhaCriptografada = new BCryptPasswordEncoder().encode(pacienteInput.getSenha());
        pacienteInput.setSenha(senhaCriptografada);
        String cpfCriptografado = new BCryptPasswordEncoder().encode(pacienteInput.getCpf());
        pacienteInput.setCpf(cpfCriptografado);
        Paciente paciente = modelMapper.map(pacienteInput, Paciente.class);
        paciente = pacienteRepository.save(paciente);
        CadastroPacienteOutput pacienteOutput = modelMapper.map(paciente, CadastroPacienteOutput.class);

        if (!emailService.enviarEmailDeBoasVindas(paciente.getEmail(), "Novo usuário cadastrado")) {
            pacienteRepository.delete(paciente);
            throw new NegocioException("Erro ao enviar o email");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteOutput);
    }


    public ResponseEntity<AtualizarPacienteOutput>atualizar(AtualizarPacienteInput pacienteInput) throws NoSuchMethodException {

        BuscarPacienteOutput pacienteOutput = buscarPorId(pacienteInput.getId()).getBody();

        Paciente paciente = modelMapper.map(pacienteOutput, Paciente.class);

        if (!paciente.getSenha().equals(pacienteInput.getSenha())){
            String senhaCriptografada = new BCryptPasswordEncoder().encode(paciente.getSenha());
            pacienteInput.setSenha(senhaCriptografada);
        }
        if (!paciente.getCpf().equals(pacienteInput.getCpf())){
            String cpfCriptografado = new  BCryptPasswordEncoder().encode(pacienteInput.getCpf());
            pacienteInput.setCpf(cpfCriptografado);
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

