package br.com.serratec.aula2.banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.serratec.aula2.banco.entity.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    
}
