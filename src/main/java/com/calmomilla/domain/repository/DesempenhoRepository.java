package com.calmomilla.domain.repository;

import com.calmomilla.domain.model.Desempenho;
import com.calmomilla.domain.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DesempenhoRepository extends JpaRepository<Desempenho,String> {

    List<Desempenho> findByUsuario(Paciente paciente);

}
