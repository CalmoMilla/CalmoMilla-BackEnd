package com.calmomilla.api.controllers;

import com.calmomilla.api.dto.output.materia.MateriaOutput;
import com.calmomilla.domain.model.Materia;
import com.calmomilla.domain.service.MateriaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("materias")
@AllArgsConstructor
public class MateriaController {

    private final MateriaService materiaService;

    @GetMapping
    public List<MateriaOutput> buscarTodas() {
        return materiaService.buscarTodas();
    }

}
