package com.calmomilla.domain.service;

import com.calmomilla.api.dto.input.rotina.AtualizarRotinaInput;
import com.calmomilla.api.dto.input.rotina.CadastroRotinaInput;
import com.calmomilla.api.dto.output.rotina.AtualizarRotinaOutput;
import com.calmomilla.api.dto.output.rotina.BuscarRotinaOutput;
import com.calmomilla.api.dto.output.rotina.CadastroRotinaOutput;
import com.calmomilla.api.dto.output.tarefa.BuscarTarefaOutput;
import com.calmomilla.domain.model.Endereco;
import com.calmomilla.domain.model.Rotina;
import com.calmomilla.domain.repository.RotinaRepository;
import com.calmomilla.domain.utils.ModelMapperUtils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class RotinaService {
    private final RotinaRepository rotinaRepository;
    private final ModelMapper modelMapper;
    private final ModelMapperUtils mapperUtils;

    public ResponseEntity<CadastroRotinaOutput> criar(CadastroRotinaInput cadastroRotinaInput) {
        Rotina rotina = modelMapper.map(cadastroRotinaInput, Rotina.class);
        Rotina rotinaSalva = rotinaRepository.save(rotina);
        CadastroRotinaOutput cadastroRotinaOutput = modelMapper.map(rotinaSalva, CadastroRotinaOutput.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(cadastroRotinaOutput);
    }

    public ResponseEntity<Void> deletar (String id) throws NoSuchMethodException {
        if (rotinaRepository.findById(id).isEmpty()) {
            throw new NoSuchMethodException("id não encontrado");
        }
        rotinaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity <BuscarRotinaOutput> buscarPorId (String id) throws NoSuchMethodException {
        if (rotinaRepository.findById(id).isEmpty()) {
            throw new NoSuchMethodException("id não encontrado");
        }
        Rotina rotina = rotinaRepository.findById(id).get();
        BuscarRotinaOutput buscarRotinaOutput = modelMapper.map(rotina, BuscarRotinaOutput.class);
        return ResponseEntity.ok(buscarRotinaOutput);
    }

    public List<BuscarRotinaOutput> buscarTodos() {
        List<Rotina> rotinas = rotinaRepository.findAll();
        List<BuscarRotinaOutput> buscarRotinas = mapperUtils.mapList(rotinas, BuscarRotinaOutput.class);
        return buscarRotinas;
    }

    public ResponseEntity<AtualizarRotinaOutput> atualizar (AtualizarRotinaInput atualizarRotina) throws NoSuchMethodException {
        BuscarRotinaOutput buscarRotinaOutput = buscarPorId(atualizarRotina.getId()).getBody();
        Rotina rotina = modelMapper.map(buscarRotinaOutput, Rotina.class);
        rotina = modelMapper.map(atualizarRotina, Rotina.class);
        Rotina rotinaSalva = rotinaRepository.save(rotina);
        AtualizarRotinaOutput atualizarRotinaOutput = modelMapper.map(rotinaSalva, AtualizarRotinaOutput.class);
        return ResponseEntity.ok(atualizarRotinaOutput);
    }

}
