package com.calmomilla.domain.service;

import com.calmomilla.api.dto.input.AuthDTO;
import com.calmomilla.api.dto.input.CadastrarDTO;
import com.calmomilla.api.dto.input.paciente.CadastroPacienteInput;
import com.calmomilla.api.dto.input.psicologo.CadastroPsicologoInput;
import com.calmomilla.api.dto.output.paciente.CadastroPacienteOutput;
import com.calmomilla.api.dto.output.psicologo.CadastroPsicologoOutput;
import com.calmomilla.api.dto.output.LoginOutput;
import com.calmomilla.api.dto.output.UsuarioOutput;
import com.calmomilla.api.configs.security.TokenService;
import com.calmomilla.domain.exception.NegocioException;
import com.calmomilla.domain.model.Paciente;
import com.calmomilla.domain.model.Psicologo;
import com.calmomilla.domain.model.Usuario;
import com.calmomilla.domain.repository.PsicologoRepository;
import com.calmomilla.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    public ResponseEntity<LoginOutput> login(AuthDTO authDTO) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(authDTO.getEmail(), authDTO.getSenha());
        var auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.gerarToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginOutput(token));

    }

    public ResponseEntity<CadastroPacienteOutput> cadastrar(CadastroPacienteInput pacienteInput) {
    return pacienteService.cadastrar(pacienteInput);
    }

    public ResponseEntity<CadastroPsicologoOutput> cadastrar(CadastroPsicologoInput psicologoInput) {

        return psicologoService.cadastrar(psicologoInput);

    }

    public ResponseEntity<?> loginGoogle(CadastroPacienteInput pacienteInput) {
        if (userRepository.findByEmail(pacienteInput.getEmail()) == null) {

            return cadastrar(pacienteInput);
        } else {
            AuthDTO authDTO = modelMapper.map(pacienteInput, AuthDTO.class);
            return login(authDTO);
        }
    }
}
