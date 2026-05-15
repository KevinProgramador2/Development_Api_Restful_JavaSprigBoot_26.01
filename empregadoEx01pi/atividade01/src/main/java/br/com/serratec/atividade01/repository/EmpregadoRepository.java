package br.com.serratec.atividade01.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.atividade01.model.Empregado;

public interface EmpregadoRepository extends JpaRepository<Empregado, Long>{

}
