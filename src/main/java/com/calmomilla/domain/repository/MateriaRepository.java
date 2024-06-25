package com.calmomilla.domain.repository;

import com.calmomilla.domain.model.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, String> {

}
