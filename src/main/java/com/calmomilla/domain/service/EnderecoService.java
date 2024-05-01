package com.calmomilla.domain.service;

import com.calmomilla.api.dto.input.endereco.AtualizarEnderecoInput;
import com.calmomilla.api.dto.input.endereco.CadastroEnderecoInput;
import com.calmomilla.api.dto.input.tarefa.AtualizarTarefaInput;
import com.calmomilla.api.dto.output.endereco.AtualizarEnderecoOutput;
import com.calmomilla.api.dto.output.endereco.BuscarEnderecoOutput;
import com.calmomilla.api.dto.output.endereco.CadastroEnderecoOutput;
import com.calmomilla.domain.model.Endereco;
import com.calmomilla.domain.model.Tarefa;
import com.calmomilla.domain.repository.EnderecoRepository;
import com.calmomilla.domain.utils.ModelMapperUtils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EnderecoService {
    private final EnderecoRepository enderecoRepository;
    private final ModelMapper modelMapper;
    private final ModelMapperUtils mapperUtils;

    public ResponseEntity<CadastroEnderecoOutput> criar(CadastroEnderecoInput cadastroEnderecoInput) {
        Endereco endereco = modelMapper.map(cadastroEnderecoInput, Endereco.class);
        Endereco enderecoSalvo = enderecoRepository.save(endereco);
        CadastroEnderecoOutput cadastroEnderecoOutput = modelMapper.map(enderecoSalvo, CadastroEnderecoOutput.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(cadastroEnderecoOutput);
    }

    public ResponseEntity<Void> deletar(String id) throws NoSuchMethodException {
        if (enderecoRepository.findById(id).isEmpty()){
            throw new NoSuchMethodException("id não encontrado");
        }
        enderecoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<BuscarEnderecoOutput> buscarPorId(String id) throws NoSuchMethodException {
        if (enderecoRepository.findById(id).isEmpty()){
            throw new NoSuchMethodException("id não encontrado");
        }

        Endereco endereco = enderecoRepository.findById(id).get();
        BuscarEnderecoOutput buscarEnderecoOutput = modelMapper.map(endereco, BuscarEnderecoOutput.class);
        return ResponseEntity.ok(buscarEnderecoOutput);
    }

    public List<BuscarEnderecoOutput> buscarTodos() {
        List<Endereco> enderecos = enderecoRepository.findAll();
        List<BuscarEnderecoOutput> enderecosOutput = mapperUtils.mapList(enderecos, BuscarEnderecoOutput.class);
        return enderecosOutput;
    }

    public ResponseEntity<AtualizarEnderecoOutput> atualizar(AtualizarEnderecoInput atualizarEndereco) throws NoSuchMethodException {
        BuscarEnderecoOutput buscarEnderecoOutput = buscarPorId(atualizarEndereco.getId()).getBody();
        Endereco endereco = modelMapper.map(buscarEnderecoOutput, Endereco.class);
        endereco = modelMapper.map(atualizarEndereco, Endereco.class);
        Endereco enderecoSalvo = enderecoRepository.save(endereco);
        AtualizarEnderecoOutput atualizarEnderecoOutput = modelMapper.map(enderecoSalvo, AtualizarEnderecoOutput.class);
        return ResponseEntity.ok(atualizarEnderecoOutput);
    }


}
