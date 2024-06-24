package com.calmomilla.domain.repository;

import com.calmomilla.domain.model.Paciente;
import com.calmomilla.domain.model.Rotina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RotinaRepository extends JpaRepository<Rotina, String> {

    List<Rotina> findRotinaByDiaRotina(LocalDate data);
    List<Rotina> findRotinasByPacientesOrderByDiaRotinaDesc (Paciente paciente);
}
