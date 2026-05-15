package br.com.serratec.atividade01.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

// Importante quando vamos fazer herança e essa tabela ta sendo herdada de outra
// tabela.
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Empregado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @NotBlank
    @Size(max = 50)
    protected String nome;

    @NotBlank
    @Size(max = 15)
    protected String cpf;

    // pode ter o salario aumentado ate 5000$
    @DecimalMin("1600.0")
    private Double salario;

    @NotBlank
    @Size(max = 10)
    protected String turno;

    @JsonBackReference // lado filho (evita loop infinito)
    @ManyToOne
    @JoinColumn(name = "id_setor")
    private Setor setor;
}