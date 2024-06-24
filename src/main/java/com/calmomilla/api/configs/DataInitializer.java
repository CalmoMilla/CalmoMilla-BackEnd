package com.calmomilla.api.configs;

import com.calmomilla.domain.model.*;
import com.calmomilla.domain.repository.*;
import com.calmomilla.domain.utils.UserRole;
import com.calmomilla.domain.utils.enums.Especializacoes;
import com.calmomilla.domain.utils.enums.Focos;
import com.calmomilla.domain.utils.enums.Genero;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component
@AllArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final JogoRepository jogoRepository;
    private final PacienteRepository pacienteRepository;
    private final PsicologoRepository psicologoRepository;
    private final TarefaRepository tarefaRepository;
    private final RotinaRepository rotinaRepository;

    @Override
    public void run(String... args) throws Exception {
        // Criar objetos Jogo

        if (jogoRepository.existsJogoByNome("Jogo da Memória")){
            System.out.println("Jogo da Memória ja existe");
        }else {
            Jogo jogo1 = new Jogo();
            jogo1.setNome("Jogo da Memória");
            jogo1.setDescricao("Jogo da Memória é um jogo clássico que desafia sua capacidade de lembrar e combinar pares de cartas. Com diversos temas e níveis de dificuldade, este jogo é perfeito para todas as idades. Melhore sua memória e divirta-se ao mesmo tempo enquanto tenta encontrar todos os pares antes do tempo acabar!");
            jogo1.setFocos(Arrays.asList(Focos.MEMORIA, Focos.ATENCAO, Focos.RELAXAMENTO));
            jogo1.setFoto("https://calmomilla-fotos.s3.sa-east-1.amazonaws.com/memoria.png");
            jogo1.setAvaliacao(0);
            jogoRepository.save(jogo1);
        }

        if (jogoRepository.existsJogoByNome("Sudoku")){
            System.out.println("Sudoku ja existe");
        }else {
            Jogo jogo2 = new Jogo();
            jogo2.setNome("Sudoku");
            jogo2.setDescricao("Sudoku é um popular jogo de quebra-cabeça numérico que testa sua lógica e raciocínio. Preencha a grade 9x9 com números de 1 a 9, garantindo que cada número apareça apenas uma vez em cada linha, coluna e região 3x3. Com vários níveis de dificuldade, Sudoku oferece horas de desafio mental e entretenimento.");
            jogo2.setFocos(Arrays.asList(Focos.RESOLUCAO_DE_PROBLEMAS, Focos.ATENCAO));
            jogo2.setFoto("https://calmomilla-fotos.s3.sa-east-1.amazonaws.com/sudoku.png");
            jogo2.setAvaliacao(0);
            jogoRepository.save(jogo2);
        }

        if (jogoRepository.existsJogoByNome("Quiz")){
            System.out.println("Quiz ja existe");
        }else {
            Jogo jogo3 = new Jogo();
            jogo3.setNome("Quiz");
            jogo3.setDescricao("O Quiz é um jogo emocionante que coloca seu conhecimento à prova com perguntas de diversas categorias, incluindo história, ciência, esportes, entretenimento e muito mais. Compita com seus amigos ou jogue sozinho para ver quem consegue a maior pontuação. Desafie-se e aprenda algo novo a cada partida!");
            jogo3.setFocos(Arrays.asList(Focos.ATENCAO, Focos.VELOCIDADE));
            jogo3.setFoto("/assets/psicologo/recursos/quiz.jpg");
            jogo3.setAvaliacao(0);
            jogoRepository.save(jogo3);
        }


        if (jogoRepository.existsJogoByNome("CacaPalavras")){
            System.out.println("CacaPalavras ja existe");
        }else {
            Jogo jogo3 = new Jogo();
            jogo3.setNome("CacaPalavras");
            jogo3.setDescricao("O Quiz é um jogo emocionante que coloca seu conhecimento à prova com perguntas de diversas categorias, incluindo história, ciência, esportes, entretenimento e muito mais. Compita com seus amigos ou jogue sozinho para ver quem consegue a maior pontuação. Desafie-se e aprenda algo novo a cada partida!");
            jogo3.setFocos(Arrays.asList(Focos.ATENCAO, Focos.RESOLUCAO_DE_PROBLEMAS));
            jogo3.setFoto("https://calmomilla-fotos.s3.sa-east-1.amazonaws.com/palavrasCruzadas.png");
            jogo3.setAvaliacao(0);
            jogoRepository.save(jogo3);
        }



        if (pacienteRepository.findByEmail("adm@gmail.com").isPresent()){

            System.out.println("o paciente adm@gmail.com ja existe");
        }else {
            Paciente usuario = new Paciente();
            usuario.setNome("Admin");
            usuario.setEmail("adm@gmail.com");
            usuario.setSenha("123456");
            var senhaUsuario = new BCryptPasswordEncoder().encode(usuario.getSenha());
            usuario.setSenha(senhaUsuario);
            usuario.setRole(UserRole.ADMIN);
            pacienteRepository.save(usuario);
        }

        if (pacienteRepository.findByEmail("dndragonbr@gmail.com").isPresent()){

            System.out.println("o paciente dndragonbr@gmail.com ja existe");
        }else {

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
            pacienteRepository.save(vitor);
        }

//        if (psicologoRepository.findByEmail("gbvjb@gmail.com").isPresent()){
//
//            System.out.println("o psicologo gbvjb@gmail.com ja existe");
//        }else {
//
//            Psicologo psicologo = new Psicologo();
//            psicologo.setNome("gabriel");
//            psicologo.setEmail("gab@gmail.com");
//            psicologo.setGenero(Genero.MASCULINO);
//            psicologo.setDataNasc(LocalDate.parse("2005-04-17"));
//            psicologo.setCpf("24094280880");
//            psicologo.setTelefone("119682102859");
//            psicologo.setSenha("123456");
//            var senhaPsicologo = new BCryptPasswordEncoder().encode(psicologo.getSenha());
//            psicologo.setSenha(senhaPsicologo);
//            psicologo.setFoto("https://lh3.googleusercontent.com/a/ACg8ocI0WJi3mbL6zITt7V2Ef4Pb4hEXS1mAL_ioJDtuPuDllqkGyQPc2A=s96-c");
//            psicologo.setEspecializacoes(List.of(Especializacoes.PSICOLOGIA_GERAL));
//            psicologo.setNumeroRegistro("4429213");
//            psicologo.setRole(UserRole.PSICOLOGO);
//            psicologoRepository.save(psicologo);
//
//        }

        if (tarefaRepository.findTarefaByLink("/jogodamemoria") != null || tarefaRepository.findTarefaByLink("/sudoku") != null || tarefaRepository.findTarefaByLink("/quiz") != null){
            System.out.println("tarefas ja adicionadas");
        }else {

            Tarefa tarefa = new Tarefa();
            List<Tarefa> tarefas = new ArrayList<>();

            tarefa.setTitulo("Jogar Jogo da Memória por 10 minutos");
            tarefa.setLink("/jogodamemoria");
            tarefa.setStatus(false);
            tarefa.setFocos(List.of(Focos.ATENCAO,Focos.MEMORIA,Focos.VELOCIDADE));

            Tarefa tarefa2 = new Tarefa();

            tarefa2.setTitulo("Jogar Sudoku por 10 minutos");
            tarefa2.setLink("/sudoku");
            tarefa2.setStatus(false);
            tarefa2.setFocos(List.of(Focos.ATENCAO,Focos.RESOLUCAO_DE_PROBLEMAS));

            Tarefa tarefa3 = new Tarefa();

            tarefa3.setTitulo("Jogar Quiz por 5 minutos");
            tarefa3.setLink("/quiz");
            tarefa3.setStatus(false);
            tarefa3.setFocos(List.of(Focos.ATENCAO,Focos.VELOCIDADE));

            tarefas.add(tarefa);
            tarefas.add(tarefa2);
            tarefas.add(tarefa3);

            tarefaRepository.saveAll(tarefas);

        }


        if (!rotinaRepository.findRotinaByDiaRotina(LocalDate.of(1, 1, 1)).isEmpty()){
            System.out.println("A rotina padrao ja existe");
        }else {
            Rotina rotinaPadrao = new Rotina();

            rotinaPadrao.setDiaRotina(LocalDate.of(1, 1, 1));
            rotinaPadrao.setStatus(false);

            Tarefa tarefamemoria =  tarefaRepository.findTarefaByLink("/jogodamemoria");
            Tarefa tarefaquiz =  tarefaRepository.findTarefaByLink("/quiz");

            rotinaPadrao.setTarefas(List.of(tarefamemoria,tarefaquiz));
            rotinaRepository.save(rotinaPadrao);
            Paciente vitor = pacienteRepository.findByEmail("dndragonbr@gmail.com").get();
            vitor.setRotinas(List.of(rotinaPadrao));
            pacienteRepository.save(vitor);
        }


        System.out.println("Dados inseridos nas tabelas com sucesso!");
    }
}
