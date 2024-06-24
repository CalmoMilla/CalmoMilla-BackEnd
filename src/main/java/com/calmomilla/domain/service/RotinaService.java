package com.calmomilla.domain.service;

import com.calmomilla.api.configs.DateTimeConfig;
import com.calmomilla.api.dto.input.paciente.AtualizarPacienteInput;
import com.calmomilla.api.dto.input.rotina.AtualizarRotinaInput;
import com.calmomilla.api.dto.input.rotina.CadastroRotinaInput;
import com.calmomilla.api.dto.output.paciente.BuscarPacienteOutput;
import com.calmomilla.api.dto.output.rotina.AtualizarRotinaOutput;
import com.calmomilla.api.dto.output.rotina.BuscarRotinaOutput;
import com.calmomilla.api.dto.output.rotina.CadastroRotinaOutput;
import com.calmomilla.domain.exception.NegocioException;
import com.calmomilla.domain.model.Desempenho;
import com.calmomilla.domain.model.Paciente;
import com.calmomilla.domain.model.Rotina;
import com.calmomilla.domain.model.Tarefa;
import com.calmomilla.domain.repository.RotinaRepository;
import com.calmomilla.domain.utils.ModelMapperUtils;
import com.calmomilla.domain.utils.enums.Focos;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@AllArgsConstructor
@Service
public class RotinaService {
    private final RotinaRepository rotinaRepository;
    private final ModelMapper modelMapper;
    private final ModelMapperUtils mapperUtils;
    private PacienteService pacienteService;
    private TarefaService tarefaService;
    private final DateTimeConfig dataEHora;

    @Transactional
    public ResponseEntity<CadastroRotinaOutput> criar(CadastroRotinaInput cadastroRotinaInput) throws NoSuchMethodException {
        Rotina rotina = gerarRotina(cadastroRotinaInput).getBody();
        assert rotina != null;
        Rotina rotinaSalva = rotinaRepository.save(rotina);
        BuscarPacienteOutput pacienteOutput = pacienteService.buscarPorId(cadastroRotinaInput.getPaciente().getId()).getBody();
        AtualizarPacienteInput pacienteInput = modelMapper.map(pacienteOutput, AtualizarPacienteInput.class);
        pacienteInput.setRotina(rotina);
        pacienteService.atualizar(pacienteInput);
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

        public ResponseEntity<?> buscarRotinaPorPaciente(String id) throws NoSuchMethodException {
        BuscarPacienteOutput pacienteOutput = pacienteService.buscarPorId(id).getBody();
            assert pacienteOutput != null;
            if (pacienteOutput.getRotinas() == null){
            throw new NegocioException("Esse usuario ainda não tem uma rotina");
        }
            Paciente paciente = modelMapper.map(pacienteOutput, Paciente.class);
           List<Rotina> rotinas = rotinaRepository.findRotinasByPacientes(List.of(paciente));
            System.out.println(rotinas);
            System.out.println(paciente );
        return ResponseEntity.ok(rotinas);

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
        System.out.println(rotinaSalva);
        AtualizarRotinaOutput atualizarRotinaOutput = modelMapper.map(rotinaSalva, AtualizarRotinaOutput.class);
        return ResponseEntity.ok(atualizarRotinaOutput);
    }

    public ResponseEntity<Rotina> gerarRotina(CadastroRotinaInput cadastroRotinaInput) throws NoSuchMethodException {
        BuscarPacienteOutput pacienteOutput = pacienteService.buscarPorId(cadastroRotinaInput.getPaciente().getId()).getBody();

        assert pacienteOutput != null;

        List<Desempenho> desempenhos = pacienteOutput.getDesempenhos();

        int jogoMemoria = 0;
        int sudoku = 0;
        int quiz = 0;

        for (Desempenho desempenho : desempenhos) {
            String nomeJogo = desempenho.getJogos().getNome();
            int nivel = desempenho.getNivel();
            double pontuacao = desempenho.getPontuacao();

            if (nomeJogo.equals("JogoMemoria")) {
                if (nivel == 1) {
                    if (pontuacao >= 8 && pontuacao <= 9) {
                        jogoMemoria += 4;
                    } else if (pontuacao >= 10 && pontuacao <= 14) {
                        jogoMemoria += 3;
                    } else if (pontuacao >= 15 && pontuacao <= 19) {
                        jogoMemoria += 2;
                    } else {
                        jogoMemoria += 1;
                    }
                } else if (nivel == 2) {
                    if (pontuacao >= 8 && pontuacao <= 9) {
                        jogoMemoria += 7;
                    } else if (pontuacao >= 10 && pontuacao <= 14) {
                        jogoMemoria += 6;
                    } else if (pontuacao >= 15 && pontuacao <= 19) {
                        jogoMemoria += 5;
                    } else {
                        jogoMemoria += 4;
                    }
                } else if (nivel == 3) {
                    if (pontuacao >= 18 && pontuacao <= 19) {
                        jogoMemoria += 11;
                    } else if (pontuacao >= 20 && pontuacao <= 24) {
                        jogoMemoria += 10;
                    } else if (pontuacao >= 25 && pontuacao <= 29) {
                        jogoMemoria += 9;
                    } else {
                        jogoMemoria += 8;
                    }
                }
            } else if (nomeJogo.equals("Sudoku")) {
                // Adicione a lógica de pontuação para Sudoku aqui
            } else if (nomeJogo.equals("Quiz")) {
                // Adicione a lógica de pontuação para Quiz aqui
            }
        }

        int menorValor = Math.min(jogoMemoria, Math.min(quiz, sudoku));
        List<Tarefa> tarefas;

        if (menorValor == jogoMemoria) {
            tarefas = tarefaService.buscarPorFoco(List.of(Focos.MEMORIA, Focos.ATENCAO)).getBody();
        } else if (menorValor == quiz) {
            tarefas = tarefaService.buscarPorFoco(List.of(Focos.VELOCIDADE, Focos.ATENCAO)).getBody();
        } else {
            tarefas = tarefaService.buscarPorFoco(List.of(Focos.RESOLUCAO_DE_PROBLEMAS, Focos.ATENCAO, Focos.MEMORIA)).getBody();
        }
        assert tarefas != null;
        Random random = new Random();
        Tarefa tarefaSelecionada = tarefas.get(random.nextInt(tarefas.size()));

        Rotina rotina = modelMapper.map(cadastroRotinaInput, Rotina.class);
        rotina.setStatus(true);
        rotina.setDiaRotina(dataEHora.brazilLocalDateTime().toLocalDate());
        rotina.setTarefas(List.of(tarefaSelecionada));

        return ResponseEntity.ok(rotina);
    }

}
