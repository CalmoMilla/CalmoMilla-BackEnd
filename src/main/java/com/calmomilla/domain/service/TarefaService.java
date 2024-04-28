package com.calmomilla.domain.service;

import com.calmomilla.api.dto.input.CadastroTarefaInput;
import com.calmomilla.api.dto.output.CadastroTarefaOutput;
import com.calmomilla.domain.model.Tarefa;
import com.calmomilla.domain.repository.TarefaRepository;
import com.calmomilla.domain.utils.ModelMapperUtils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TarefaService {
    private final TarefaRepository tarefaRepository;
    private final ModelMapper modelMapper;
    private final ModelMapperUtils modelMapperUtils;

    public ResponseEntity<CadastroTarefaOutput> criarTarefa(CadastroTarefaInput cadastroTarefaInput) {
        Tarefa tarefa = modelMapper.map(cadastroTarefaInput, Tarefa.class);
        Tarefa tarefaSalva =  tarefaRepository.save(tarefa);
        CadastroTarefaOutput tarefaOutput = modelMapper.map(tarefaSalva, CadastroTarefaOutput.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaOutput);
    }
}
