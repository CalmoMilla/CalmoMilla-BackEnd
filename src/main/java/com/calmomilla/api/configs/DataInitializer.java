package com.calmomilla.api.configs;

import com.calmomilla.domain.model.Jogo;
import com.calmomilla.domain.repository.JogoRepository;
import com.calmomilla.domain.utils.enums.Focos;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@AllArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final JogoRepository jogoRepository;

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

        // Persistir os objetos usando o EntityManager
        jogoRepository.save(jogo1);
        jogoRepository.save(jogo2);
        jogoRepository.save(jogo3);



        System.out.println("Dados inseridos na tabela jogos com sucesso!");
    }
}
