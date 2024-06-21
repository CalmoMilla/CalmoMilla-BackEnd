package com.calmomilla.domain.service;

import com.calmomilla.api.dto.input.rotina.AtualizarRotinaInput;
import com.calmomilla.api.dto.input.rotina.CadastroRotinaInput;
import com.calmomilla.api.dto.output.paciente.BuscarPacienteOutput;
import com.calmomilla.api.dto.output.rotina.AtualizarRotinaOutput;
import com.calmomilla.api.dto.output.rotina.BuscarRotinaOutput;
import com.calmomilla.api.dto.output.rotina.CadastroRotinaOutput;
import com.calmomilla.domain.exception.NegocioException;
import com.calmomilla.domain.model.Desempenho;
import com.calmomilla.domain.model.Rotina;
import com.calmomilla.domain.repository.RotinaRepository;
import com.calmomilla.domain.utils.ModelMapperUtils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
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
    private PacienteService pacienteService;

    public ResponseEntity<CadastroRotinaOutput> criar(CadastroRotinaInput cadastroRotinaInput) throws NoSuchMethodException {
        gerarRotina(cadastroRotinaInput.getPaciente().getId());
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

        public ResponseEntity<BuscarRotinaOutput> buscarRotinaPorPaciente(String id) throws NoSuchMethodException {
        BuscarPacienteOutput pacienteOutput = pacienteService.buscarPorId(id).getBody();
            assert pacienteOutput != null;
            if (pacienteOutput.getRotina() == null){
            throw new NegocioException("Esse usuario ainda não tem uma rotina");
        }

        return buscarPorId(pacienteOutput.getRotina().getId());

    }


    public List<BuscarRotinaOutput> buscarTodos() {
        List<Rotina> rotinas = rotinaRepository.findAll();
        return mapperUtils.mapList(rotinas, BuscarRotinaOutput.class);
    }

    public ResponseEntity<AtualizarRotinaOutput> atualizar (AtualizarRotinaInput atualizarRotina) throws NoSuchMethodException {
        BuscarRotinaOutput buscarRotinaOutput = buscarPorId(atualizarRotina.getId()).getBody();
        Rotina rotina = modelMapper.map(buscarRotinaOutput, Rotina.class);
        rotina = modelMapper.map(atualizarRotina, Rotina.class);
        Rotina rotinaSalva = rotinaRepository.save(rotina);
        AtualizarRotinaOutput atualizarRotinaOutput = modelMapper.map(rotinaSalva, AtualizarRotinaOutput.class);
        return ResponseEntity.ok(atualizarRotinaOutput);
    }

    public ResponseEntity<?>gerarRotina(String id) throws NoSuchMethodException {

        BuscarPacienteOutput  pacienteOutput = pacienteService.buscarPorId(id).getBody();

        assert pacienteOutput != null;
        var focos = pacienteOutput.getFocos();
        List<Desempenho> desempenho = pacienteOutput.getDesempenhos();

        // 20+
        // M >= 20 == PESSIMO
        // M >= 15 +-
        //M >=10 BEM
        //8 == MT BOM

        int jogoMemoria = 0;
        int sudoku = 0;
        int quiz = 0;

        for (Desempenho desempenho1: desempenho){
            if (desempenho1.getJogos().getNome().equals("JogoMemoria")) {
                //jogo da memoria nivel 1
                if (desempenho1.getNivel() == 1 && desempenho1.getPontuacao() >= 8 && desempenho1.getPontuacao() <= 9) {
                   jogoMemoria += 4;
                } else if (desempenho1.getNivel() == 1 && desempenho1.getPontuacao() >= 10 && desempenho1.getPontuacao() <= 14) {
                    jogoMemoria += 3;
                }else if (desempenho1.getNivel() == 1 && desempenho1.getPontuacao() >= 15 && desempenho1.getPontuacao() <= 19) {
                    jogoMemoria += 2;
                }else {
                    jogoMemoria += 1;
                }
                //jogo da memoria nivel 2
                if (desempenho1.getNivel() == 2 && desempenho1.getPontuacao() >= 8 && desempenho1.getPontuacao() <= 9) {
                    jogoMemoria += 7;
                } else if (desempenho1.getNivel() == 2 && desempenho1.getPontuacao() >= 10 && desempenho1.getPontuacao() <= 14) {
                    jogoMemoria += 6;
                }else if (desempenho1.getNivel() == 2 && desempenho1.getPontuacao() >= 15 && desempenho1.getPontuacao() <= 19) {
                    jogoMemoria += 5;
                }else {
                    jogoMemoria += 4;
                }
                //jogo da memoria nivel 3
                if (desempenho1.getNivel() == 3 && desempenho1.getPontuacao() >= 18 && desempenho1.getPontuacao() <= 19) {
                    jogoMemoria += 11;
                } else if (desempenho1.getNivel() == 3 && desempenho1.getPontuacao() >= 20 && desempenho1.getPontuacao() <= 24) {
                    jogoMemoria += 10;
                }else if (desempenho1.getNivel() == 3 && desempenho1.getPontuacao() >= 25 && desempenho1.getPontuacao() <= 29) {
                    jogoMemoria += 9;
                }else {
                    jogoMemoria += 8;
                }

            }
            if (desempenho1.getJogos().getNome().equals("Sudoku")){
                //sudoku
            }



            if (desempenho1.getJogos().getNome().equals("Quiz")){
                //Quiz
            }

          int menorValor =  Math.min(jogoMemoria,Math.min(quiz,sudoku));

            System.out.println(menorValor);


        }
        return null;
    }

}
