package com.calmomilla.api.configs;

import com.calmomilla.domain.model.Jogo;
import com.calmomilla.domain.model.Paciente;
import com.calmomilla.domain.model.Psicologo;
import com.calmomilla.domain.model.Usuario;
import com.calmomilla.domain.repository.JogoRepository;
import com.calmomilla.domain.repository.PacienteRepository;
import com.calmomilla.domain.repository.PsicologoRepository;
import com.calmomilla.domain.repository.UserRepository;
import com.calmomilla.domain.utils.UserRole;
import com.calmomilla.domain.utils.enums.Especializacoes;
import com.calmomilla.domain.utils.enums.Focos;
import com.calmomilla.domain.utils.enums.Genero;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final JogoRepository jogoRepository;
    private final PacienteRepository pacienteRepository;
    private final PsicologoRepository psicologoRepository;

    @Override
    public void run(String... args) throws Exception {
        // Criar objetos Jogo

        Jogo jogo1 = new Jogo();
        jogo1.setNome("Jogo da Memória");
        jogo1.setDescricao("Jogo da Memória é um jogo clássico que desafia sua capacidade de lembrar e combinar pares de cartas. Com diversos temas e níveis de dificuldade, este jogo é perfeito para todas as idades. Melhore sua memória e divirta-se ao mesmo tempo enquanto tenta encontrar todos os pares antes do tempo acabar!");
        jogo1.setFocos(Arrays.asList(Focos.MEMORIA, Focos.ATENCAO, Focos.RELAXAMENTO));
        jogo1.setFoto("url_da_foto_do_jogo_da_memoria");
        jogo1.setAvaliacao(0);

        Jogo jogo2 = new Jogo();
        jogo2.setNome("Sudoku");
        jogo2.setDescricao("Sudoku é um popular jogo de quebra-cabeça numérico que testa sua lógica e raciocínio. Preencha a grade 9x9 com números de 1 a 9, garantindo que cada número apareça apenas uma vez em cada linha, coluna e região 3x3. Com vários níveis de dificuldade, Sudoku oferece horas de desafio mental e entretenimento.");
        jogo2.setFocos(Arrays.asList(Focos.RESOLUCAO_DE_PROBLEMAS, Focos.ATENCAO));
        jogo2.setFoto("url_da_foto_do_sudoku");
        jogo2.setAvaliacao(0);

        Jogo jogo3 = new Jogo();
        jogo3.setNome("Quiz");
        jogo3.setDescricao("O Quiz é um jogo emocionante que coloca seu conhecimento à prova com perguntas de diversas categorias, incluindo história, ciência, esportes, entretenimento e muito mais. Compita com seus amigos ou jogue sozinho para ver quem consegue a maior pontuação. Desafie-se e aprenda algo novo a cada partida!");
        jogo3.setFocos(Arrays.asList(Focos.ATENCAO, Focos.VELOCIDADE));
        jogo3.setFoto("url_da_foto_do_quiz");
        jogo3.setAvaliacao(0);

        Paciente usuario = new Paciente();
        usuario.setNome("Admin");
        usuario.setEmail("adm@gmail.com");
        usuario.setSenha("123456");
        var senhaUsuario = new BCryptPasswordEncoder().encode(usuario.getSenha());
        usuario.setSenha(senhaUsuario);
        usuario.setRole(UserRole.ADMIN);

        Paciente vitor = new Paciente();
        vitor.setNome("vitor");
        vitor.setEmail("dndragonbr@gmail.com");
        vitor.setSenha("123456");
        var senhaVitor = new BCryptPasswordEncoder().encode(vitor.getSenha());
        vitor.setSenha(senhaVitor);
        vitor.setGenero(Genero.MASCULINO);
        vitor.setDataNasc(LocalDate.parse("2006-03-28"));
        vitor.setCpf("52439200883");
        vitor.setTelefone("11987492156");
        vitor.setRole(UserRole.PACIENTE);
        vitor.setFoto("https://lh3.googleusercontent.com/a/ACg8ocL9IDPYfcaUn1-5L9VZsxgMVlXffSES0P6PcJPCwQKnRgfSPLjl=s96-c-rg-br100");

        Psicologo psicologo = new Psicologo();
        psicologo.setNome("gabriel");
        psicologo.setEmail("gbvjb@gmail.com");
        psicologo.setGenero(Genero.MASCULINO);
        psicologo.setDataNasc(LocalDate.parse("2005-04-17"));
        psicologo.setCpf("24094280880");
        psicologo.setTelefone("119682102859");
        psicologo.setSenha("010203cd");
        var senhaPsicologo = new BCryptPasswordEncoder().encode(psicologo.getSenha());
        psicologo.setSenha(senhaPsicologo);
        psicologo.setFoto("https://lh3.googleusercontent.com/a/ACg8ocI0WJi3mbL6zITt7V2Ef4Pb4hEXS1mAL_ioJDtuPuDllqkGyQPc2A=s96-c");
        psicologo.setEspecializacoes(List.of(Especializacoes.PSICOLOGIA_GERAL));
        psicologo.setNumeroRegistro("4429213");
        psicologo.setRole(UserRole.PSICOLOGO);


        // Persistir os objetos usando o EntityManager
        pacienteRepository.save(usuario);
        pacienteRepository.save(vitor);
        psicologoRepository.save(psicologo);
        jogoRepository.save(jogo1);
        jogoRepository.save(jogo2);
        jogoRepository.save(jogo3);



        System.out.println("Dados inseridos na tabela jogos com sucesso!");
    }
}
