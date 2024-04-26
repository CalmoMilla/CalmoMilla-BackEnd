package com.calmomilla.domain.service;

import com.calmomilla.api.dto.input.AuthDTO;
import com.calmomilla.api.dto.input.CadastrarDTO;
import com.calmomilla.api.dto.output.LoginOutput;
import com.calmomilla.api.dto.output.UsuarioOutput;
import com.calmomilla.api.configs.security.TokenService;
import com.calmomilla.domain.model.Usuario;
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


    public ResponseEntity<LoginOutput> login(AuthDTO authDTO) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(authDTO.getEmail(), authDTO.getSenha());
        var auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.gerarToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginOutput(token));

    }

    public ResponseEntity<UsuarioOutput> cadastrar(CadastrarDTO cadastrarDTO) {

        if (userRepository.findByEmail(cadastrarDTO.getEmail()) != null) return ResponseEntity.badRequest().build();


        String senhaCriptografada = new BCryptPasswordEncoder().encode(cadastrarDTO.getSenha());
        cadastrarDTO.setSenha(senhaCriptografada);
        Usuario usuario = modelMapper.map(cadastrarDTO, Usuario.class);
        Usuario usuarioCadastrado = userRepository.save(usuario);
        UsuarioOutput usuarioOutput = modelMapper.map(usuarioCadastrado, UsuarioOutput.class);

        return new ResponseEntity<>(usuarioOutput, HttpStatus.CREATED);
    }


    public ResponseEntity<?> loginGoogle(CadastrarDTO cadastrarDTO) {
        if (userRepository.findByEmail(cadastrarDTO.getEmail()) == null) {
          return cadastrar(cadastrarDTO);
        }else {
            AuthDTO authDTO = modelMapper.map(cadastrarDTO, AuthDTO.class);
            return login(authDTO);
        }
    }
}
