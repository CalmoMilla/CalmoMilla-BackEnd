package com.calmomilla.domain.repository;

import com.calmomilla.domain.model.Psicologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PsicologoRepository extends JpaRepository<Psicologo,String> {

    Optional<Psicologo> findByEmail(String email);

    boolean existsByCpfOrEmailOrTelefone(String cpf, String email, String telefone);


}
