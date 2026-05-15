package br.com.serratec.aula5.model;

import br.com.serratec.aula5.enums.Categoria;
import br.com.serratec.aula5.enums.Combustivel;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Caracteristica {
    private String renavam;
    private String chassi;
    private Integer ano;
    private String cor;

    @Enumerated(EnumType.STRING) // O @Enumerated vai definir o tipo de ENUM usando os parametos
    private Categoria categoria;

    @Enumerated(EnumType.ORDINAL)
    private Combustivel combustivel;
}
