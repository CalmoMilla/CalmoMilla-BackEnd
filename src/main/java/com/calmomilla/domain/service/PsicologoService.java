package com.calmomilla.domain.service;


import com.calmomilla.api.dto.input.psicologo.AtualizarPsicologoInput;
import com.calmomilla.api.dto.input.psicologo.CadastroPsicologoInput;
import com.calmomilla.api.dto.input.verificacao.VerificacaoDTO;
import com.calmomilla.api.dto.output.paciente.BuscarPacienteEmailOutput;
import com.calmomilla.api.dto.output.psicologo.AtualizarPsicologoOutput;
import com.calmomilla.api.dto.output.psicologo.BuscarPsicologoEmailOutput;
import com.calmomilla.api.dto.output.psicologo.BuscarPsicologoOutput;
import com.calmomilla.api.dto.output.psicologo.CadastroPsicologoOutput;
import com.calmomilla.domain.exception.NegocioException;
import com.calmomilla.domain.model.Paciente;
import com.calmomilla.domain.model.Psicologo;
import com.calmomilla.domain.repository.PsicologoRepository;

import com.calmomilla.domain.utils.ModelMapperUtils;
import com.calmomilla.domain.utils.VerificacaoCpf;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PsicologoService {

    private final PsicologoRepository psicologoRepository;
    private final ModelMapper modelMapper;
    private final ModelMapperUtils mapperUtils;
    private final EmailService emailService;
    private final VerificacaoCpf verificacaoCpf;
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

    public ResponseEntity<BuscarPsicologoEmailOutput> buscarInfo(Principal principal) throws NoSuchMethodException {
        Optional<Psicologo> psicologo = psicologoRepository.findByEmail(principal.getName());

        if (psicologo.isEmpty()) {
            throw new NoSuchMethodException("Email não encontrado");
        }
        BuscarPsicologoEmailOutput psicologoEmailOutput = modelMapper.map(psicologo.get(), BuscarPsicologoEmailOutput.class);
        return ResponseEntity.ok(psicologoEmailOutput);
    }


    public ResponseEntity<CadastroPsicologoOutput> cadastrar(CadastroPsicologoInput psicologoInput) throws NoSuchMethodException, ParseException {

        if (psicologoRepository.existsByCpfOrEmailOrTelefone(psicologoInput.getCpf(), psicologoInput.getEmail(), psicologoInput.getTelefone())) {
            throw new DataIntegrityViolationException("Recurso está em uso");
        }

        if (verificacaoCpf.enviarDados(new VerificacaoDTO(psicologoInput.getCpf(),psicologoInput.getDataNasc())).getStatusCode() != HttpStatus.OK){
            throw new NegocioException("seu cpf ou data de nascimente estão invalidos! verifique");
        }
        var senhaCriptografada = new BCryptPasswordEncoder().encode(psicologoInput.getSenha());
        psicologoInput.setSenha(senhaCriptografada);
        String cpfCriptografado = new  BCryptPasswordEncoder().encode(psicologoInput.getCpf());
        psicologoInput.setCpf(cpfCriptografado);
        Psicologo psicologo = modelMapper.map(psicologoInput, Psicologo.class);
        Psicologo psicologoCadastrado = psicologoRepository.save(psicologo);

        CadastroPsicologoOutput psicologoOutput = modelMapper.map(psicologoCadastrado, CadastroPsicologoOutput.class);

        if (!emailService.enviarEmailDeBoasVindas(psicologo.getEmail(), "Novo usuário cadastrado")){
            deletar(psicologo.getId());
            throw new NegocioException("Erro ao enviar o email");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(psicologoOutput);

    }

    public ResponseEntity<AtualizarPsicologoOutput> atualizar(AtualizarPsicologoInput psicologoInput) throws NoSuchMethodException {

        BuscarPsicologoOutput buscarPsicologoOutput = buscarPorId(psicologoInput.getId()).getBody();

        Psicologo psicologo = modelMapper.map(buscarPsicologoOutput,Psicologo.class);

        if (!psicologo.getSenha().equals(psicologoInput.getSenha())){
            String senhaCriptografada = new BCryptPasswordEncoder().encode(psicologoInput.getSenha());
            psicologoInput.setSenha(senhaCriptografada);
        }
        System.out.println(psicologo.getCpf());
        if (!psicologo.getCpf().equals(psicologoInput.getCpf())){
            String cpfCriptografado = new  BCryptPasswordEncoder().encode(psicologoInput.getCpf());
            psicologoInput.setCpf(cpfCriptografado);
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

