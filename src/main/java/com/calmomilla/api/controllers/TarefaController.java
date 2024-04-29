package com.calmomilla.api.controllers;


import com.calmomilla.api.dto.input.tarefa.AtualizarTarefaInput;
import com.calmomilla.api.dto.input.tarefa.CadastroTarefaInput;
import com.calmomilla.api.dto.output.tarefa.AtualizarTarefaOutput;
import com.calmomilla.api.dto.output.tarefa.BuscarTarefaOutput;
import com.calmomilla.api.dto.output.tarefa.CadastroTarefaOutput;
import com.calmomilla.domain.service.TarefaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tarefa")
@AllArgsConstructor
public class TarefaController {

    TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<CadastroTarefaOutput> cadastrar(@RequestBody @Valid CadastroTarefaInput tarefaInput) {
        return tarefaService.criar(tarefaInput);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BuscarTarefaOutput> buscarPorId(@PathVariable String id) throws NoSuchMethodException {
        return tarefaService.buscarPorId(id);
    }

    @GetMapping
    public List<BuscarTarefaOutput> buscarTodos() {
        return tarefaService.buscarTodos();
    }

    @PutMapping
    public ResponseEntity<AtualizarTarefaOutput> atualizar(
            @RequestBody @Valid AtualizarTarefaInput atualizarTarefa) throws NoSuchMethodException{
        return tarefaService.atualizar(atualizarTarefa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) throws NoSuchMethodException  {
        return tarefaService.deletar(id);
    }

}
