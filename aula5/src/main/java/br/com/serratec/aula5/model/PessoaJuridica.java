package br.com.serratec.aula5.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class PessoaJuridica extends Fornecedor {

    private String nome;
    private String cnpj;
    private String nomeFantasia;
    private String inscricaoEstadual;

    

}
