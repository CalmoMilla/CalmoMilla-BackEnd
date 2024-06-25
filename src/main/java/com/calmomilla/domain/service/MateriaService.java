package com.calmomilla.domain.service;

import com.calmomilla.api.dto.output.materia.MateriaOutput;
import com.calmomilla.domain.model.Materia;
import com.calmomilla.domain.repository.MateriaRepository;
import com.calmomilla.domain.utils.ModelMapperUtils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MateriaService {

    private final MateriaRepository materiaRepository;
    private final ModelMapperUtils modelMapperUtils;

    public List<MateriaOutput> buscarTodas() {
        List<Materia> materias = materiaRepository.findAll();
        return modelMapperUtils.mapList(materias, MateriaOutput.class);
    }
}
