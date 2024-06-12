package com.calmomilla.domain.repository;

import com.calmomilla.domain.model.Emocao;
import com.calmomilla.domain.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmocaoRepository extends JpaRepository<Emocao,String> {

    List<Emocao> findByPacienteOrderByDataRegistroDesc(Paciente paciente);

}
