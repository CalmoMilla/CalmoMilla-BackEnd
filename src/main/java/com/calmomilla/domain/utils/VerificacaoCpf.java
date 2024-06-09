package com.calmomilla.domain.utils;

import com.calmomilla.api.dto.input.verificacao.VerificacaoDTO;
import com.calmomilla.domain.exception.NegocioException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@Service
@AllArgsConstructor
public class VerificacaoCpf {

    @Value("${api.infosimples.url}")
    private String apiUrl;

    @Value("${api.infosimples.token}")
    private String apiToken;

    private final ObjectMapper objectMapper;

    public ResponseEntity<?> enviarDados(VerificacaoDTO verificacaoDTO) throws ParseException {
        // Dados que você deseja enviar
        String origem = "web";
        String timeout = "300";

        // Criando o corpo da requisição
        String requestBody = "cpf=" + verificacaoDTO.getCpf() +
                "&birthdate=" + verificacaoDTO.getDataNasc() +
                "&origem=" + origem +
                "&token=" + apiToken +
                "&timeout=" + timeout;

        // Configurando o cabeçalho da requisição
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // Configurando a requisição
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

        // Realizando a chamada para a API
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response;
        try {
            response = restTemplate.postForEntity(apiUrl, request, String.class);
        } catch (Exception e) {
            throw new NegocioException("Erro ao conectar com a API: " + e.getMessage());
        }

        // Pegando a resposta
        String responseBody = response.getBody();
        JsonNode data;
        try {
            JsonNode root = objectMapper.readTree(responseBody);
            data = root.path("data").get(0); // Assumindo que há apenas um objeto de dados
        } catch (Exception e) {
            throw new NegocioException("Erro ao processar a resposta da API: " + e.getMessage());
        }

        if (data == null) {
            throw new NegocioException("Erro ao verificar o seu CPF e data de nascimento. Verifique se digitou corretamente.");
        }

        String dataNasc = data.get("data_nascimento").asText();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            Date date = dateFormat.parse(dataNasc);
            LocalDate dataNascimento = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate dataAtual = LocalDate.now();

            // Calcular a idade
            int idade = Period.between(dataNascimento, dataAtual).getYears();
            System.out.println("Idade: " + idade);

            // Verificar se a pessoa é maior de idade
            if (idade >= 18) {
                return ResponseEntity.ok("A pessoa é maior de idade.");
            } else {
                return ResponseEntity.badRequest().body("Você é menor de idade, não pode efetuar cadastro.");
            }
        } catch (ParseException e) {
            System.out.println("Erro ao formatar a data: " + e.getMessage());
            throw new NegocioException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
