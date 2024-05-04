package com.calmomilla.domain.repository;

import com.calmomilla.domain.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente,String> {

    UserDetails findByEmail(String login);

}
