package com.calmomilla.api.controllers;

import com.calmomilla.domain.model.Relaxamento;
import com.calmomilla.domain.repository.RelaxamentoRepository;
import com.calmomilla.domain.utils.enums.CategoriaRelaxamento;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/relaxamentos")
public class RelaxamentoController {

    private final RelaxamentoRepository relaxamentoRepository;

    @GetMapping("/meditacao")
    public List<Relaxamento> buscarMeditacao(){

        return relaxamentoRepository.findRelaxamentoByCategoria(CategoriaRelaxamento.MEDITACAO);

    }

    @GetMapping("/yoga")
    public List<Relaxamento> buscarYoga(){
        return relaxamentoRepository.findRelaxamentoByCategoria(CategoriaRelaxamento.YOGA);
    }


    @GetMapping("/respiracao")
    public  List<Relaxamento> buscarRespiracao() {
        return relaxamentoRepository.findRelaxamentoByCategoria(CategoriaRelaxamento.RESPIRACAO);
    }
}
