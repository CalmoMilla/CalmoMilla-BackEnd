package com.calmomilla.domain.repository;

import com.calmomilla.domain.model.Relaxamento;
import com.calmomilla.domain.utils.enums.CategoriaRelaxamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelaxamentoRepository extends JpaRepository<Relaxamento,String> {

    List<Relaxamento> findRelaxamentoByCategoria(CategoriaRelaxamento categoria);

}
