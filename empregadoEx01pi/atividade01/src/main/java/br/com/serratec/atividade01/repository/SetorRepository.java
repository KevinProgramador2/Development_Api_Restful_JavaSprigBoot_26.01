package br.com.serratec.atividade01.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.atividade01.model.Setor;

public interface SetorRepository extends JpaRepository<Setor, Long> {

}
