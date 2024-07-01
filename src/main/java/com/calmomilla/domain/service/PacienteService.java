package com.calmomilla.domain.service;
import com.calmomilla.api.dto.input.paciente.AtualizarPacienteInput;
import com.calmomilla.api.dto.input.paciente.CadastroPacienteInput;
import com.calmomilla.api.dto.input.paciente.FavoritarPsicologoInput;
import com.calmomilla.api.dto.input.verificacao.VerificacaoDTO;
import com.calmomilla.api.dto.output.paciente.AtualizarPacienteOutput;
import com.calmomilla.api.dto.output.paciente.BuscarPacienteEmailOutput;
import com.calmomilla.api.dto.output.paciente.BuscarPacienteOutput;
import com.calmomilla.api.dto.output.paciente.CadastroPacienteOutput;
import com.calmomilla.api.dto.output.psicologo.BuscarPsicologoOutput;
import com.calmomilla.domain.exception.NegocioException;
import com.calmomilla.domain.model.Emocao;
import com.calmomilla.domain.model.Paciente;

import com.calmomilla.domain.model.Psicologo;
import com.calmomilla.domain.model.Rotina;
import com.calmomilla.domain.repository.EmocaoRepository;
import com.calmomilla.domain.repository.PacienteRepository;
import com.calmomilla.domain.repository.RotinaRepository;
import com.calmomilla.domain.utils.ModelMapperUtils;
import com.calmomilla.domain.utils.VerificacaoCpf;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;

@Service
@AllArgsConstructor
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final EmocaoRepository emocaoRepository;
    private final PsicologoService psicologoService;
    private RotinaRepository rotinaRepository;
    private final ModelMapper modelMapper;
    private final ModelMapperUtils mapperUtils;
    private EmailService emailService;
    private VerificacaoCpf verificacaoCpf;

    public List<BuscarPacienteOutput> buscarTodos(){
        List<Paciente> pacientes = pacienteRepository.findAll();
        return mapperUtils.mapList(pacientes, BuscarPacienteOutput.class);
    }

    public ResponseEntity<BuscarPacienteOutput> buscarPorId(String id) throws NoSuchMethodException {
        Optional<Paciente> paciente = pacienteRepository.findById(id);

        if (paciente.isEmpty()) {
            throw new NoSuchMethodException("id não encontrado");
        }
        BuscarPacienteOutput pacienteOutput = modelMapper.map(paciente.get(), BuscarPacienteOutput.class);
        return ResponseEntity.ok(pacienteOutput);
    }
    @GetMapping("/buscarInfo")
    public ResponseEntity<BuscarPacienteEmailOutput> buscarInfo(Principal principal) throws NoSuchMethodException {
        Optional<Paciente> paciente = pacienteRepository.findByEmail(principal.getName());

        if (paciente.isEmpty()) {
            throw new NoSuchMethodException("Email não encontrado");
        }

        Paciente pacienteEntity = paciente.get();
        List<Emocao> emocaoList = emocaoRepository.findByPacienteOrderByDataRegistroDesc(pacienteEntity);

        LocalDate hoje = LocalDate.now();
        boolean precisaPreencherQuestionario = false;

        if (!emocaoList.isEmpty()) {
            if (!emocaoList.get(0).getDataRegistro().isEqual(hoje)){
                precisaPreencherQuestionario = true;
            }
        } else {
            precisaPreencherQuestionario = true;
        }

        BuscarPacienteEmailOutput pacienteOutput = modelMapper.map(pacienteEntity, BuscarPacienteEmailOutput.class);
        pacienteOutput.setPrecisaPreencherQuestionario(precisaPreencherQuestionario);

        return ResponseEntity.ok(pacienteOutput);
    }

    public ResponseEntity<BuscarPacienteEmailOutput> buscarPorEmail(String email) throws NoSuchMethodException {
        Optional<Paciente> paciente = pacienteRepository.findByEmail(email);
        if (paciente.isEmpty()) {
            throw new NoSuchMethodException("Email não encontrado");
        }
        BuscarPacienteEmailOutput pacienteOutput = modelMapper.map(paciente.get(), BuscarPacienteEmailOutput.class);
        return ResponseEntity.ok(pacienteOutput);
    }


    @Transactional
    public ResponseEntity<CadastroPacienteOutput> cadastrar(CadastroPacienteInput pacienteInput) throws ParseException {

        if (pacienteRepository.existsByCpfOrEmailOrTelefone(pacienteInput.getCpf(), pacienteInput.getEmail(), pacienteInput.getTelefone())) {
            throw new DataIntegrityViolationException("Recurso está em uso");
        }

//        var verificaCpf = verificacaoService.enviarDados(new VerificacaoDTO(pacienteInput.getCpf(),pacienteInput.getDataNasc()));
//
//        if (verificaCpf.getStatusCode() != HttpStatus.OK){
//            throw new NegocioException(String.valueOf(verificaCpf.getBody()));
//
//        }

        verificacaoCpf.verificarLocalmente(
                new VerificacaoDTO(pacienteInput.getCpf(), pacienteInput.getDataNasc()));

        String senhaCriptografada = new BCryptPasswordEncoder().encode(pacienteInput.getSenha());
        pacienteInput.setSenha(senhaCriptografada);
        String cpfCriptografado = new BCryptPasswordEncoder().encode(pacienteInput.getCpf());
        pacienteInput.setCpf(cpfCriptografado);
        Paciente paciente = modelMapper.map(pacienteInput, Paciente.class);
        var data = LocalDate.of(1, 1, 1);
        List<Rotina> rotinaPadrao = rotinaRepository.findRotinaByDiaRotina(data);
        Rotina rotinaUsuario = new Rotina();

        rotinaUsuario.setDiaRotina(rotinaPadrao.get(0).getDiaRotina());
        rotinaUsuario.setStatus(rotinaPadrao.get(0).isStatus());
        rotinaUsuario.setTarefas(new ArrayList<>(rotinaPadrao.get(0).getTarefas()));

        rotinaUsuario =  rotinaRepository.save(rotinaUsuario);
        paciente.setRotinas(List.of(rotinaUsuario));
        paciente =  pacienteRepository.save(paciente);

        Optional<Rotina> rotinaPega = rotinaRepository.findById(rotinaUsuario.getId());


        if (rotinaPega.isEmpty()){
            throw new NegocioException("Rotina não encontrada");
        }

        Rotina rotinaMapper = new Rotina();

        rotinaMapper.setId(rotinaPega.get().getId());
        rotinaMapper.setDiaRotina(rotinaPega.get().getDiaRotina());
        rotinaMapper.setStatus(rotinaPega.get().isStatus());
        rotinaMapper.setTarefas(rotinaPega.get().getTarefas());

        rotinaMapper.setPacientes(List.of(paciente));


        rotinaRepository.save(rotinaMapper);


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

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(pacienteInput.getSenha(), paciente.getSenha()) && !Objects.equals(pacienteInput.getSenha(), paciente.getSenha())){
            String senhaCriptografada = new BCryptPasswordEncoder().encode(pacienteInput.getSenha());
            pacienteInput.setSenha(senhaCriptografada);
        } else {
            assert pacienteOutput != null;
            pacienteInput.setSenha(pacienteOutput.getSenha());
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


    public ResponseEntity<AtualizarPacienteOutput> favoritar(FavoritarPsicologoInput favoritarPsicologoInput) throws NoSuchMethodException {
        Optional<Paciente> paciente = pacienteRepository.findById(favoritarPsicologoInput.getIdPaciente());

        BuscarPsicologoOutput buscarPsicologoOutput = psicologoService.buscarPorId(favoritarPsicologoInput.getIdPsicologo()).getBody();
        if (paciente.isEmpty()) {
            throw new NoSuchMethodException("Paciente não encontrado");
        }

        Psicologo psicologo = modelMapper.map(buscarPsicologoOutput, Psicologo.class);

        Paciente pacienteEncontrado = modelMapper.map(paciente.get(), Paciente.class);

        List<Psicologo> psicologosSalvos = pacienteEncontrado.getPsicologos();

        psicologosSalvos.add(psicologo);

        pacienteEncontrado.setPsicologos(psicologosSalvos);
        AtualizarPacienteInput atualizarPacienteInput = modelMapper.map(pacienteEncontrado, AtualizarPacienteInput.class);

        return atualizar(atualizarPacienteInput);

    }

    public ResponseEntity<Void> deletarPsi(FavoritarPsicologoInput favoritarPsicologoInput) throws NoSuchMethodException {

        Optional<Paciente> pacienteOptional = pacienteRepository.findById(favoritarPsicologoInput.getIdPaciente());

        if (pacienteOptional.isEmpty()) {
            throw new NoSuchMethodException("Paciente não encontrado");
        }

        Paciente paciente = pacienteOptional.get();

        BuscarPsicologoOutput buscarPsicologoOutput = psicologoService.buscarPorId(favoritarPsicologoInput.getIdPsicologo()).getBody();

        if (buscarPsicologoOutput == null) {
            throw new NoSuchMethodException("Psicologo não encontrado");
        }

        String psicologoId = buscarPsicologoOutput.getId();

        paciente.getPsicologos().removeIf(psicologo -> psicologo.getId().equals(psicologoId));

        pacienteRepository.save(paciente);

        return ResponseEntity.noContent().build();
    }

}