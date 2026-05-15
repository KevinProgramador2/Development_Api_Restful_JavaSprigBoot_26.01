package br.com.serratec.aula5.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class PessoaFisica extends Fornecedor {

    private String ci;
    private String orgaoExpedidor;
    private String cpf;

}
