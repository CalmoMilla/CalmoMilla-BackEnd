package com.calmomilla.domain.service;

import com.calmomilla.api.dto.input.emocao.CadastrarEmocaoInput;
import com.calmomilla.api.dto.output.CadastrarEmocaoOutput;
import com.calmomilla.api.dto.output.emocao.BuscarEmocaoOutput;
import com.calmomilla.api.dto.output.paciente.BuscarPacienteOutput;
import com.calmomilla.domain.model.Emocao;
import com.calmomilla.domain.model.Paciente;
import com.calmomilla.domain.repository.EmocaoRepository;
import com.calmomilla.domain.utils.Emocoes;
import com.calmomilla.domain.utils.ModelMapperUtils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmocaoService {

    private final ModelMapperUtils mapperUtils;
    private final EmocaoRepository emocaoRepository;
    private final ModelMapper modelMapper;
    private final PacienteService pacienteService;

    public ResponseEntity<List<BuscarEmocaoOutput>>buscarEmocoes(){

        List<Emocao> emocaos = emocaoRepository.findAll();

        List<BuscarEmocaoOutput> emocaoOutput = mapperUtils.mapList(emocaos,BuscarEmocaoOutput.class);

        return ResponseEntity.ok(emocaoOutput);
    }

    public ResponseEntity<BuscarEmocaoOutput>buscarEmocaoPorId(String id) throws NoSuchMethodException {

        Optional<Emocao> emocao = emocaoRepository.findById(id);

        if (emocao.isEmpty()){
            throw new NoSuchMethodException("id não encontrado");
        }
        BuscarEmocaoOutput emocaoOutput = modelMapper.map(emocao.get(), BuscarEmocaoOutput.class);

        return ResponseEntity.ok(emocaoOutput);
    }

    public ResponseEntity<List<BuscarEmocaoOutput>>buscarEmocaoPorIdPaciente(String id) throws NoSuchMethodException {

        BuscarPacienteOutput pacienteOutput = pacienteService.buscarPorId(id).getBody();
        Paciente paciente = modelMapper.map(pacienteOutput,Paciente.class);
        List<Emocao> emocaos = emocaoRepository.findByPaciente(paciente);
        List<BuscarEmocaoOutput> emocaoOutput = mapperUtils.mapList(emocaos, BuscarEmocaoOutput.class);
        return ResponseEntity.ok(emocaoOutput);

    }

    public ResponseEntity<CadastrarEmocaoOutput>cadastrar(CadastrarEmocaoInput emocaoInput){

        var triste = 0;
        var feliz = 0;
        var meioTermo = 0;

        for (int i = 0; i < emocaoInput.getEmocoes().size(); i++){

            Emocoes emocao = emocaoInput.getEmocoes().get(i);

            if (emocao == Emocoes.TRISTE) {
                triste++;
            } else if (emocao == Emocoes.FELIZ) {
                feliz++;
            } else {
                meioTermo++;
            }
        }

        int maiorEntreDois = Math.max(triste, feliz);
        int maiorEntreTres = Math.max(maiorEntreDois, meioTermo);

        String mensagem;
        if (maiorEntreTres == meioTermo) {

            emocaoInput.setDescricao(String.valueOf(Emocoes.MEIOTERMO));
            mensagem = "Você parece estar em um meio termo. Estamos aqui para apoiar você!";

        } else if (maiorEntreTres == feliz) {
            emocaoInput.setDescricao(String.valueOf(Emocoes.FELIZ));
            mensagem = "Que ótimo! Você está se sentindo feliz!";
        } else {
            mensagem = "Parece que você está se sentindo triste. Estamos aqui para ajudar!";
            emocaoInput.setDescricao(String.valueOf(Emocoes.TRISTE));
        }
        emocaoInput.setDataRegistro(LocalDate.now());
        Emocao emocao = modelMapper.map(emocaoInput,Emocao.class);

       Emocao emocaoSalva =  emocaoRepository.save(emocao);

        CadastrarEmocaoOutput emocaoOutput = modelMapper.map(emocaoSalva, CadastrarEmocaoOutput.class);
        emocaoOutput.setMensagem(mensagem);
        return ResponseEntity.ok(emocaoOutput);
        }

    }

