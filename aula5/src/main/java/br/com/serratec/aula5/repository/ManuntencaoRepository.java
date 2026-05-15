package br.com.serratec.aula5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.aula5.model.Manuntencao;

public interface ManuntencaoRepository extends JpaRepository<Manuntencao, Long> {

}
