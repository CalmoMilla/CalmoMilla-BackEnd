package com.calmomilla.domain.repository;

import com.calmomilla.domain.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente,String> {


    boolean existsByCpfOrEmailOrTelefone(String cpf, String email, String telefone);

}
