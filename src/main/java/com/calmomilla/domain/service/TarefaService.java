package com.calmomilla.domain.service;


import com.calmomilla.api.dto.input.tarefa.AtualizarTarefaInput;
import com.calmomilla.api.dto.input.tarefa.CadastroTarefaInput;
import com.calmomilla.api.dto.output.tarefa.AtualizarTarefaOutput;
import com.calmomilla.api.dto.output.tarefa.BuscarTarefaOutput;
import com.calmomilla.api.dto.output.tarefa.CadastroTarefaOutput;
import com.calmomilla.domain.model.Tarefa;
import com.calmomilla.domain.repository.TarefaRepository;
import com.calmomilla.domain.utils.ModelMapperUtils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TarefaService {
    private final TarefaRepository tarefaRepository;
    private final ModelMapper modelMapper;
    private final ModelMapperUtils mapperUtils;

    public ResponseEntity<CadastroTarefaOutput> criar(CadastroTarefaInput cadastroTarefaInput) {
        Tarefa tarefa = modelMapper.map(cadastroTarefaInput, Tarefa.class);
        Tarefa tarefaSalva =  tarefaRepository.save(tarefa);
        CadastroTarefaOutput tarefaOutput = modelMapper.map(tarefaSalva, CadastroTarefaOutput.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaOutput);
    }

    public ResponseEntity<Void> deletar(String id) throws NoSuchMethodException {
        if (tarefaRepository.findById(id).isEmpty()) {
            throw new NoSuchMethodException("id não encontrado");
        }
        tarefaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<BuscarTarefaOutput> buscarPorId(String id) throws NoSuchMethodException {
        if (tarefaRepository.findById(id).isEmpty()){
            throw new NoSuchMethodException("id não encontrado");
        }

        Tarefa tarefa = tarefaRepository.findById(id).get();
        BuscarTarefaOutput buscarTarefaOutput = modelMapper.map(tarefa, BuscarTarefaOutput.class);
        return ResponseEntity.ok(buscarTarefaOutput);
    }

    public List<BuscarTarefaOutput> buscarTodos() {
        List<Tarefa> tarefas = tarefaRepository.findAll();
        List<BuscarTarefaOutput> tarefaOutputs = mapperUtils.mapList(tarefas, BuscarTarefaOutput.class);
        return tarefaOutputs;
    }

    public ResponseEntity<AtualizarTarefaOutput> atualizar (AtualizarTarefaInput atualizarTarefa) throws NoSuchMethodException {
       BuscarTarefaOutput buscarTarefaOutput = buscarPorId(atualizarTarefa.getId()).getBody();
       Tarefa tarefa = modelMapper.map(buscarTarefaOutput, Tarefa.class);
       tarefa = modelMapper.map(atualizarTarefa,Tarefa.class);
       Tarefa tarefaSalva = tarefaRepository.save(tarefa);
       AtualizarTarefaOutput atualizarTarefaOutput = modelMapper.map(tarefaSalva, AtualizarTarefaOutput.class);
       return ResponseEntity.ok(atualizarTarefaOutput);
    }
}
