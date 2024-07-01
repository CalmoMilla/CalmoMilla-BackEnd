package com.calmomilla.api.controllers;

import com.calmomilla.api.dto.input.desempenho.AdicionarDesempenhoInput;
import com.calmomilla.api.dto.output.desempenho.AdicionarDesempenhoOutput;
import com.calmomilla.api.dto.output.desempenho.BuscarDesempenhoOutput;
import com.calmomilla.api.dto.output.desempenho.BuscarDesempenhoUsuarioOutput;
import com.calmomilla.api.dto.output.desempenho.BuscarEstatisticasOutput;
import com.calmomilla.domain.service.DesempenhoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/desempenhos")
@AllArgsConstructor
public class DesempenhoController {

    private final DesempenhoService desempenhoService;
    @GetMapping("/{id}")
    public ResponseEntity<BuscarDesempenhoOutput> buscarPorId(@PathVariable String id){
        return desempenhoService.buscarPorId(id);
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<BuscarDesempenhoUsuarioOutput>> buscarPorIdUsuario(@PathVariable String id) throws NoSuchMethodException {
        return desempenhoService.buscarPorIdUsuario(id);
    }

    @GetMapping("/usuario/estatistica/{id}")
    public ResponseEntity<BuscarEstatisticasOutput> buscarPorEstatistica(@PathVariable String id) throws NoSuchMethodException {
        return desempenhoService.buscarEstatisticas(id);
    }

    @PostMapping
    public ResponseEntity<AdicionarDesempenhoOutput>adicionar(@RequestBody @Valid AdicionarDesempenhoInput desempenhoInput) throws NoSuchMethodException {
        return desempenhoService.adicionar(desempenhoInput);
    }

}
