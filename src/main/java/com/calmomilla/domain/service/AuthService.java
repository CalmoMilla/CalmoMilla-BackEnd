package com.calmomilla.domain.service;

import com.calmomilla.api.dto.input.AuthDTO;
import com.calmomilla.api.dto.input.paciente.CadastroPacienteInput;
import com.calmomilla.api.dto.input.psicologo.CadastroPsicologoInput;
import com.calmomilla.api.dto.input.recuperarSenha.RecuperarSenhaInput;
import com.calmomilla.api.dto.output.paciente.CadastroPacienteOutput;
import com.calmomilla.api.dto.output.psicologo.CadastroPsicologoOutput;
import com.calmomilla.api.dto.output.LoginOutput;

import com.calmomilla.api.configs.security.TokenService;

import com.calmomilla.api.dto.output.recuperarSenha.RecuperarSenhaOutput;
import com.calmomilla.domain.exception.NegocioException;
import com.calmomilla.domain.model.Usuario;

import com.calmomilla.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final PsicologoService psicologoService;
    private final PacienteService pacienteService;
    private final EmailService emailService;

    public ResponseEntity<LoginOutput> login(AuthDTO authDTO) {
        String token = null;
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(authDTO.getEmail(), authDTO.getSenha());
            var auth = authenticationManager.authenticate(usernamePassword);
            token = tokenService.gerarToken((Usuario) auth.getPrincipal());
            return ResponseEntity.ok(new LoginOutput(token));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(e.getMessage());
        } catch (Exception e) {
            throw new NegocioException("Erro ao autenticar o usuario");
        }

    }

    public ResponseEntity<CadastroPacienteOutput> cadastrar(CadastroPacienteInput pacienteInput) throws NoSuchMethodException {
    return pacienteService.cadastrar(pacienteInput);
    }

    public ResponseEntity<CadastroPsicologoOutput> cadastrar(CadastroPsicologoInput psicologoInput) throws NoSuchMethodException {

        return psicologoService.cadastrar(psicologoInput);

    }

    public ResponseEntity<?> loginGoogle(CadastroPacienteInput pacienteInput) throws NoSuchMethodException {
        if (userRepository.findByEmail(pacienteInput.getEmail()) == null) {

            return cadastrar(pacienteInput);
        } else {
            AuthDTO authDTO = modelMapper.map(pacienteInput, AuthDTO.class);
            return login(authDTO);
        }
    }

    public ResponseEntity<RecuperarSenhaOutput> recuperarSenha(RecuperarSenhaInput recuperarSenhaInput) {

        if (emailService.enviarEmailDeRecuperarSenha(recuperarSenhaInput.getEmail(), "Recupere a sua conta!"));
        RecuperarSenhaOutput senhaOutput = modelMapper.map(recuperarSenhaInput, RecuperarSenhaOutput.class);
        senhaOutput.setResposta("Email enviado com sucesso para "+senhaOutput.getEmail());
        return ResponseEntity.ok(senhaOutput);

    }
}
