package com.calmomilla.domain.service;

import com.calmomilla.api.configs.security.TokenService;
import com.calmomilla.api.dto.input.AuthDTO;
import com.calmomilla.api.dto.input.loginGoogle.LoginGoogleInput;
import com.calmomilla.api.dto.input.paciente.AtualizarPacienteInput;
import com.calmomilla.api.dto.input.paciente.CadastroPacienteInput;
import com.calmomilla.api.dto.input.psicologo.AtualizarPsicologoInput;
import com.calmomilla.api.dto.input.psicologo.CadastroPsicologoInput;
import com.calmomilla.api.dto.input.recuperarSenha.RecuperarSenhaInput;
import com.calmomilla.api.dto.input.recuperarSenhaRedefinir.RecuperarSenhaRedefinirInput;
import com.calmomilla.api.dto.output.LoginOutput;
import com.calmomilla.api.dto.output.paciente.CadastroPacienteOutput;
import com.calmomilla.api.dto.output.psicologo.CadastroPsicologoOutput;
import com.calmomilla.api.dto.output.recuperarSenha.RecuperarSenhaOutput;
import com.calmomilla.domain.exception.NegocioException;
import com.calmomilla.domain.model.Usuario;
import com.calmomilla.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;

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
        String token;
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(authDTO.getEmail(), authDTO.getSenha());
            var auth = authenticationManager.authenticate(usernamePassword);
            token = tokenService.gerarToken((Usuario) auth.getPrincipal());
            return ResponseEntity.ok(new LoginOutput(token));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new NegocioException("Erro ao autenticar o usuario");
        }

    }

    public ResponseEntity<CadastroPacienteOutput> cadastrar(CadastroPacienteInput pacienteInput) throws NoSuchMethodException, ParseException {
    return pacienteService.cadastrar(pacienteInput);
    }

    public ResponseEntity<CadastroPsicologoOutput> cadastrar(CadastroPsicologoInput psicologoInput) throws NoSuchMethodException, ParseException {

        return psicologoService.cadastrar(psicologoInput);

    }

    public ResponseEntity<?> loginGoogle(LoginGoogleInput loginGoogleInput) {
       var usuario = userRepository.findByEmail(loginGoogleInput.getEmail());
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        } else {
            if (loginGoogleInput.getSenha().isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            return login(new AuthDTO(loginGoogleInput.getEmail(), loginGoogleInput.getSenha()));
        }
    }

    public ResponseEntity<RecuperarSenhaOutput> recuperarSenha(RecuperarSenhaInput recuperarSenhaInput) {
      var usuario = userRepository.findByEmail(recuperarSenhaInput.getEmail());
       if (usuario == null){throw new NegocioException("Email não encontrado");}
        emailService.enviarEmailDeRecuperarSenha(recuperarSenhaInput.getEmail(), "Recupere a sua conta!");
        RecuperarSenhaOutput senhaOutput = new RecuperarSenhaOutput(recuperarSenhaInput.getEmail(), "Email enviado com sucesso para "+recuperarSenhaInput.getEmail());
        return ResponseEntity.ok(senhaOutput);
    }

    public ResponseEntity<RecuperarSenhaOutput> recuperarSenhaRedefinir(RecuperarSenhaRedefinirInput recuperarSenhaInput) throws NoSuchMethodException {
        var usuario = pacienteService.buscarPorEmail(recuperarSenhaInput.getEmail());
        int usuarioEscolhido = 0;
        if (usuario == null){
            usuario = psicologoService.buscarPorEmail(recuperarSenhaInput.getEmail());
            usuarioEscolhido = 1;
            if (usuario == null) {
                throw new NegocioException("falha ao encontrar usuario");
            }
        }

      var usuarioEncontrado = usuario.getBody();
        assert usuarioEncontrado != null;
        if (usuarioEscolhido == 0){
            var usuarioEncontradoPaciente = pacienteService.buscarPorId(usuarioEncontrado.getId()).getBody();
            assert usuarioEncontradoPaciente != null;
            usuarioEncontradoPaciente.setSenha(recuperarSenhaInput.getSenha());
            AtualizarPacienteInput pacienteInput = modelMapper.map(usuarioEncontradoPaciente, AtualizarPacienteInput.class);
            if (pacienteService.atualizar(pacienteInput).getStatusCode() != HttpStatus.OK){
                throw new NegocioException("falha ao salvar usuario");
            }
            return ResponseEntity.ok(new RecuperarSenhaOutput(pacienteInput.getEmail(),"Senha alterada com sucesso"));
        } else {
            var usuarioEncontradoPsicologo = psicologoService.buscarPorId(usuarioEncontrado.getId()).getBody();
            assert usuarioEncontradoPsicologo != null;
            var senhaCriptografada = new BCryptPasswordEncoder().encode(recuperarSenhaInput.getSenha());
            usuarioEncontradoPsicologo.setSenha(senhaCriptografada);
            AtualizarPsicologoInput psicologoInput = modelMapper.map(usuarioEncontradoPsicologo, AtualizarPsicologoInput.class);
            if (psicologoService.atualizar(psicologoInput).getStatusCode() != HttpStatus.OK){
                throw new NegocioException("falha ao salvar usuario");
            }
            return ResponseEntity.ok(new RecuperarSenhaOutput(psicologoInput.getEmail(),"Senha alterada com sucesso"));
        }


    }
}
