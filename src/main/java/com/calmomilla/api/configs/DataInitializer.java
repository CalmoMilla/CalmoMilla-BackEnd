package com.calmomilla.api.configs;

import com.calmomilla.domain.model.*;
import com.calmomilla.domain.repository.*;
import com.calmomilla.domain.utils.UserRole;
import com.calmomilla.domain.utils.enums.CategoriaRelaxamento;
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
    private final RelaxamentoRepository relaxamentoRepository;

    @Override
    public void run(String... args) throws Exception {
        // Criar objetos Jogo

        if (jogoRepository.existsJogoByNome("Jogo da Memória")) {
            System.out.println("Jogo da Memória ja existe");
        } else {
            Jogo jogo1 = new Jogo();
            jogo1.setNome("Jogo da Memória");
            jogo1.setDescricao("Teste sua memória combinando pares de cartas neste divertido desafio!");
            jogo1.setFocos(Arrays.asList(Focos.MEMORIA, Focos.ATENCAO, Focos.RELAXAMENTO));
            jogo1.setFoto("https://calmomilla-fotos.s3.sa-east-1.amazonaws.com/memoria.png");
            jogo1.setAvaliacao(0);
            jogo1.setLink("/jogomemoria");
            jogoRepository.save(jogo1);
        }

        if (jogoRepository.existsJogoByNome("Sudoku")) {
            System.out.println("Sudoku ja existe");
        } else {
            Jogo jogo2 = new Jogo();
            jogo2.setNome("Sudoku");
            jogo2.setDescricao("Preencha a grade com números de 1 a 9 sem repetir em linhas e colunas.");
            jogo2.setFocos(Arrays.asList(Focos.RESOLUCAO_DE_PROBLEMAS, Focos.ATENCAO));
            jogo2.setFoto("https://calmomilla-fotos.s3.sa-east-1.amazonaws.com/sudoku.png");
            jogo2.setAvaliacao(0);
            jogo2.setLink("/sudoku");
            jogoRepository.save(jogo2);
        }

        if (jogoRepository.existsJogoByNome("Quiz")) {
            System.out.println("Quiz ja existe");
        } else {
            Jogo jogo3 = new Jogo();
            jogo3.setNome("Quiz");
            jogo3.setDescricao("Responda a perguntas variadas e veja quantas você acerta!");
            jogo3.setFocos(Arrays.asList(Focos.ATENCAO, Focos.VELOCIDADE));
            jogo3.setFoto("https://calmomilla-fotos.s3.sa-east-1.amazonaws.com/quiz.jpg");
            jogo3.setLink("/quiz");
            jogo3.setAvaliacao(0);
            jogoRepository.save(jogo3);
        }


        if (jogoRepository.existsJogoByNome("CacaPalavras")) {
            System.out.println("CacaPalavras ja existe");
        } else {
            Jogo jogo4 = new Jogo();
            jogo4.setNome("CacaPalavras");
            jogo4.setDescricao("Encontre as palavras escondidas na grade e desafie seu vocabulário!");
            jogo4.setFocos(Arrays.asList(Focos.ATENCAO, Focos.RESOLUCAO_DE_PROBLEMAS));
            jogo4.setFoto("https://calmomilla-fotos.s3.sa-east-1.amazonaws.com/palavrasCruzadas.png");
            jogo4.setLink("/cacapalavras");
            jogo4.setAvaliacao(0);
            jogoRepository.save(jogo4);
        }


        if (pacienteRepository.findByEmail("adm@gmail.com").isPresent()) {

            System.out.println("o paciente adm@gmail.com ja existe");
        } else {
            Paciente usuario = new Paciente();
            usuario.setNome("Admin");
            usuario.setEmail("adm@gmail.com");
            usuario.setSenha("123456");
            var senhaUsuario = new BCryptPasswordEncoder().encode(usuario.getSenha());
            usuario.setSenha(senhaUsuario);
            usuario.setRole(UserRole.ADMIN);
            pacienteRepository.save(usuario);
        }

        if (pacienteRepository.findByEmail("dndragonbr@gmail.com").isPresent()) {

            System.out.println("o paciente dndragonbr@gmail.com ja existe");
        } else {

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

        if (psicologoRepository.findByEmail("gbvjb@gmail.com").isPresent()) {

            System.out.println("o psicologo gbvjb@gmail.com ja existe");
        } else {

            Psicologo psicologo = new Psicologo();
            psicologo.setNome("gabriel");
            psicologo.setEmail("gab@gmail.com");
            psicologo.setGenero(Genero.MASCULINO);
            psicologo.setDataNasc(LocalDate.parse("2005-04-17"));
            psicologo.setCpf("24094280880");
            psicologo.setTelefone("119682102859");
            psicologo.setSenha("123456");
            var senhaPsicologo = new BCryptPasswordEncoder().encode(psicologo.getSenha());
            psicologo.setSenha(senhaPsicologo);
            psicologo.setFoto("https://lh3.googleusercontent.com/a/ACg8ocI0WJi3mbL6zITt7V2Ef4Pb4hEXS1mAL_ioJDtuPuDllqkGyQPc2A=s96-c");
            psicologo.setEspecializacoes(List.of(Especializacoes.PSICOLOGIA_GERAL));
            psicologo.setNumeroRegistro("4429213");
            psicologo.setRole(UserRole.PSICOLOGO);
            psicologoRepository.save(psicologo);

        }

        if (tarefaRepository.findTarefaByLink("/jogodamemoria") != null || tarefaRepository.findTarefaByLink("/sudoku") != null || tarefaRepository.findTarefaByLink("/quiz") != null) {
            System.out.println("tarefas ja adicionadas");
        } else {

            Tarefa tarefa = new Tarefa();
            List<Tarefa> tarefas = new ArrayList<>();

            tarefa.setTitulo("Jogar Jogo da Memória por 10 minutos");
            tarefa.setLink("/jogodamemoria");
            tarefa.setStatus(false);
            tarefa.setFocos(List.of(Focos.ATENCAO, Focos.MEMORIA, Focos.VELOCIDADE));

            Tarefa tarefa2 = new Tarefa();

            tarefa2.setTitulo("Jogar Sudoku por 10 minutos");
            tarefa2.setLink("/sudoku");
            tarefa2.setStatus(false);
            tarefa2.setFocos(List.of(Focos.ATENCAO, Focos.RESOLUCAO_DE_PROBLEMAS));

            Tarefa tarefa3 = new Tarefa();

            tarefa3.setTitulo("Jogar Quiz por 5 minutos");
            tarefa3.setLink("/quiz");
            tarefa3.setStatus(false);
            tarefa3.setFocos(List.of(Focos.ATENCAO, Focos.VELOCIDADE));

            tarefas.add(tarefa);
            tarefas.add(tarefa2);
            tarefas.add(tarefa3);

            tarefaRepository.saveAll(tarefas);

        }


        if (!rotinaRepository.findRotinaByDiaRotina(LocalDate.of(1, 1, 1)).isEmpty()) {
            System.out.println("A rotina padrao ja existe");
        } else {
            Rotina rotinaPadrao = new Rotina();

            rotinaPadrao.setDiaRotina(LocalDate.of(1, 1, 1));
            rotinaPadrao.setStatus(false);

            Tarefa tarefamemoria = tarefaRepository.findTarefaByLink("/jogodamemoria");
            Tarefa tarefaquiz = tarefaRepository.findTarefaByLink("/quiz");

            rotinaPadrao.setTarefas(List.of(tarefamemoria, tarefaquiz));
            rotinaRepository.save(rotinaPadrao);
            Paciente vitor = pacienteRepository.findByEmail("dndragonbr@gmail.com").get();
            vitor.setRotinas(List.of(rotinaPadrao));
            pacienteRepository.save(vitor);
        }

        if (!relaxamentoRepository.findRelaxamentoByCategoria(CategoriaRelaxamento.MEDITACAO).isEmpty()) {

            System.out.println("Já foi persistido os relaxamentos");

        } else {

        Relaxamento meditacao01 = new Relaxamento();
        meditacao01.setLink("xrkeHKLBcY4?si=pqBTjexETq6WAJqQ");
        meditacao01.setCategoria(CategoriaRelaxamento.MEDITACAO);
        relaxamentoRepository.save(meditacao01);

        Relaxamento meditacao02 = new Relaxamento();
        meditacao02.setLink("xrkeHKLBcY4?si=pqBTjexETq6WAJqQ");
        meditacao02.setCategoria(CategoriaRelaxamento.MEDITACAO);
        relaxamentoRepository.save(meditacao02);

        Relaxamento meditacao03 = new Relaxamento();
        meditacao03.setLink("xrkeHKLBcY4?si=pqBTjexETq6WAJqQ");
        meditacao03.setCategoria(CategoriaRelaxamento.MEDITACAO);
        relaxamentoRepository.save(meditacao03);

        Relaxamento meditacao04 = new Relaxamento();
        meditacao04.setLink("xrkeHKLBcY4?si=pqBTjexETq6WAJqQ");
        meditacao04.setCategoria(CategoriaRelaxamento.MEDITACAO);
        relaxamentoRepository.save(meditacao04);

        Relaxamento meditacao05 = new Relaxamento();
        meditacao05.setLink("xrkeHKLBcY4?si=pqBTjexETq6WAJqQ");
        meditacao05.setCategoria(CategoriaRelaxamento.MEDITACAO);
        relaxamentoRepository.save(meditacao05);

        Relaxamento meditacao06 = new Relaxamento();
        meditacao06.setLink("xrkeHKLBcY4?si=pqBTjexETq6WAJqQ");
        meditacao06.setCategoria(CategoriaRelaxamento.MEDITACAO);
        relaxamentoRepository.save(meditacao06);

        Relaxamento meditacao07 = new Relaxamento();
        meditacao07.setLink("xrkeHKLBcY4?si=pqBTjexETq6WAJqQ");
        meditacao07.setCategoria(CategoriaRelaxamento.MEDITACAO);
        relaxamentoRepository.save(meditacao07);

        Relaxamento meditacao08 = new Relaxamento();
        meditacao08.setLink("xrkeHKLBcY4?si=pqBTjexETq6WAJqQ");
        meditacao08.setCategoria(CategoriaRelaxamento.MEDITACAO);
        relaxamentoRepository.save(meditacao08);

        Relaxamento meditacao09 = new Relaxamento();
        meditacao09.setLink("xrkeHKLBcY4?si=pqBTjexETq6WAJqQ");
        meditacao09.setCategoria(CategoriaRelaxamento.MEDITACAO);
        relaxamentoRepository.save(meditacao09);

        Relaxamento meditacao10 = new Relaxamento();
        meditacao10.setLink("xrkeHKLBcY4?si=pqBTjexETq6WAJqQ");
        meditacao10.setCategoria(CategoriaRelaxamento.MEDITACAO);
        relaxamentoRepository.save(meditacao10);
    }

        if(!relaxamentoRepository.findRelaxamentoByCategoria(CategoriaRelaxamento.YOGA).isEmpty()){
            System.out.println("Já foi persistido os dados do Yoga");
        }else {

            Relaxamento yoga01 = new Relaxamento();
            yoga01.setLink("Z_ksB-TO5GA?si=PXerSmoLICoICM3r");
            yoga01.setCategoria(CategoriaRelaxamento.YOGA);

            relaxamentoRepository.save(yoga01);


            Relaxamento yoga02 = new Relaxamento();
            yoga02.setLink("Z_ksB-TO5GA?si=PXerSmoLICoICM3r");
            yoga02.setCategoria(CategoriaRelaxamento.YOGA);
            relaxamentoRepository.save(yoga02);


            Relaxamento yoga03 = new Relaxamento();
            yoga03.setLink("Z_ksB-TO5GA?si=PXerSmoLICoICM3r");
            yoga03.setCategoria(CategoriaRelaxamento.YOGA);
            relaxamentoRepository.save(yoga03);

            Relaxamento yoga04 = new Relaxamento();
            yoga04.setLink("Z_ksB-TO5GA?si=PXerSmoLICoICM3r");
            yoga04.setCategoria(CategoriaRelaxamento.YOGA);
            relaxamentoRepository.save(yoga04);

            Relaxamento yoga05 = new Relaxamento();
            yoga05.setLink("Z_ksB-TO5GA?si=PXerSmoLICoICM3r");
            yoga05.setCategoria(CategoriaRelaxamento.YOGA);
            relaxamentoRepository.save(yoga05);

            Relaxamento yoga06 = new Relaxamento();
            yoga06.setLink("Z_ksB-TO5GA?si=PXerSmoLICoICM3r");
            yoga06.setCategoria(CategoriaRelaxamento.YOGA);
            relaxamentoRepository.save(yoga06);

            Relaxamento yoga07 = new Relaxamento();
            yoga07.setLink("Z_ksB-TO5GA?si=PXerSmoLICoICM3r");
            yoga07.setCategoria(CategoriaRelaxamento.YOGA);
            relaxamentoRepository.save(yoga07);

            Relaxamento yoga08 = new Relaxamento();
            yoga08.setLink("Z_ksB-TO5GA?si=PXerSmoLICoICM3r");
            yoga08.setCategoria(CategoriaRelaxamento.YOGA);
            relaxamentoRepository.save(yoga08);


            Relaxamento yoga09 = new Relaxamento();
            yoga09.setLink("Z_ksB-TO5GA?si=PXerSmoLICoICM3r");
            yoga09.setCategoria(CategoriaRelaxamento.YOGA);
            relaxamentoRepository.save(yoga09);

            Relaxamento yoga10 = new Relaxamento();
            yoga10.setLink("Z_ksB-TO5GA?si=PXerSmoLICoICM3r");
            yoga10.setCategoria(CategoriaRelaxamento.YOGA);
            relaxamentoRepository.save(yoga10);
        }

        if (!relaxamentoRepository.findRelaxamentoByCategoria(CategoriaRelaxamento.RESPIRACAO).isEmpty()){
            System.out.println("Já foi persistido os dados de Meditação");
        }else {
            Relaxamento respiracao01 = new Relaxamento();
            respiracao01.setLink("a1i5rgjunpw?si=-ldZIM_bbBmZq6rj");
            respiracao01.setCategoria(CategoriaRelaxamento.RESPIRACAO);

            relaxamentoRepository.save(respiracao01);

            Relaxamento respiracao02 = new Relaxamento();
            respiracao02.setLink("a1i5rgjunpw?si=-ldZIM_bbBmZq6rj");
            respiracao02.setCategoria(CategoriaRelaxamento.RESPIRACAO);

            relaxamentoRepository.save(respiracao02);

            Relaxamento respiracao03 = new Relaxamento();
            respiracao03.setLink("a1i5rgjunpw?si=-ldZIM_bbBmZq6rj");
            respiracao03.setCategoria(CategoriaRelaxamento.RESPIRACAO);

            relaxamentoRepository.save(respiracao03);

            Relaxamento respiracao04 = new Relaxamento();
            respiracao04.setLink("a1i5rgjunpw?si=-ldZIM_bbBmZq6rj");
            respiracao04.setCategoria(CategoriaRelaxamento.RESPIRACAO);

            relaxamentoRepository.save(respiracao04);

            Relaxamento respiracao05 = new Relaxamento();
            respiracao05.setLink("a1i5rgjunpw?si=-ldZIM_bbBmZq6rj");
            respiracao05.setCategoria(CategoriaRelaxamento.RESPIRACAO);

            relaxamentoRepository.save(respiracao05);

            Relaxamento respiracao06 = new Relaxamento();
            respiracao06.setLink("a1i5rgjunpw?si=-ldZIM_bbBmZq6rj");
            respiracao06.setCategoria(CategoriaRelaxamento.RESPIRACAO);

            relaxamentoRepository.save(respiracao06);

            Relaxamento respiracao07 = new Relaxamento();
            respiracao07.setLink("a1i5rgjunpw?si=-ldZIM_bbBmZq6rj");
            respiracao07.setCategoria(CategoriaRelaxamento.RESPIRACAO);

            relaxamentoRepository.save(respiracao07);

            Relaxamento respiracao08 = new Relaxamento();
            respiracao08.setLink("a1i5rgjunpw?si=-ldZIM_bbBmZq6rj");
            respiracao08.setCategoria(CategoriaRelaxamento.RESPIRACAO);

            relaxamentoRepository.save(respiracao08);

            Relaxamento respiracao09 = new Relaxamento();
            respiracao09.setLink("a1i5rgjunpw?si=-ldZIM_bbBmZq6rj");
            respiracao09.setCategoria(CategoriaRelaxamento.RESPIRACAO);

            relaxamentoRepository.save(respiracao09);

            Relaxamento respiracao10 = new Relaxamento();
            respiracao10.setLink("a1i5rgjunpw?si=-ldZIM_bbBmZq6rj");
            respiracao10.setCategoria(CategoriaRelaxamento.RESPIRACAO);

            relaxamentoRepository.save(respiracao10);
        }
        System.out.println("Dados inseridos nas tabelas com sucesso!");
    }
}
