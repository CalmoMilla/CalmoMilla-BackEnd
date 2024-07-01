package com.calmomilla.domain.repository;

import com.calmomilla.domain.model.Tarefa;
import com.calmomilla.domain.utils.enums.Focos;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, String> {


    @Query("SELECT t FROM Tarefa t JOIN t.focos f WHERE f IN :focos")
    List<Tarefa> findTarefasByFocosIn(@Param("focos") List<Focos> focos);

    List<Tarefa> findTarefaByLink(String link);

}
