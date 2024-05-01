package com.calmomilla.api.controllers;

import com.calmomilla.api.dto.input.endereco.AtualizarEnderecoInput;
import com.calmomilla.api.dto.input.endereco.CadastroEnderecoInput;
import com.calmomilla.api.dto.output.endereco.AtualizarEnderecoOutput;
import com.calmomilla.api.dto.output.endereco.BuscarEnderecoOutput;
import com.calmomilla.api.dto.output.endereco.CadastroEnderecoOutput;
import com.calmomilla.domain.service.EnderecoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("endereco")
@AllArgsConstructor
public class EnderecoController {

    EnderecoService enderecoService;

    @PostMapping
    public ResponseEntity<CadastroEnderecoOutput> cadastrar(@RequestBody @Valid CadastroEnderecoInput cadastroEnderecoInput) {
        return enderecoService.criar(cadastroEnderecoInput);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BuscarEnderecoOutput> buscarPorId(@PathVariable String id) throws NoSuchMethodException {
        return enderecoService.buscarPorId(id);
    }

    @GetMapping()
    public List<BuscarEnderecoOutput> buscarTodos(){
        return enderecoService.buscarTodos();
    }

    @PutMapping
    public ResponseEntity<AtualizarEnderecoOutput> atualizar
            (@RequestBody @Valid AtualizarEnderecoInput atualizarEndereco) throws NoSuchMethodException {
        return enderecoService.atualizar(atualizarEndereco);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) throws NoSuchMethodException {
        return enderecoService.deletar(id);
    }

}
