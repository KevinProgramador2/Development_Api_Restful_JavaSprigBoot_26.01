package br.com.serratec.trabalhovalendonota01.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.trabalhovalendonota01.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

}
