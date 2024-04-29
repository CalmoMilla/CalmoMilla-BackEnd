package com.calmomilla.api.controllers;


import com.calmomilla.api.dto.input.CadastroTarefaInput;
import com.calmomilla.api.dto.output.CadastroTarefaOutput;
import com.calmomilla.domain.service.TarefaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tarefa")
@AllArgsConstructor
public class TarefaController {

    TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<CadastroTarefaOutput> cadastrar(@RequestBody @Valid CadastroTarefaInput tarefaInput) {
        return tarefaService.criarTarefa(tarefaInput);
    }
}
