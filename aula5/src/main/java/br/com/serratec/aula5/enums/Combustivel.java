package br.com.serratec.aula5.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.serratec.aula5.exceptions.EnumvalidationException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Combustivel {
    ETANOL(1,
            "ETANOL"),
    FLEX(2,
            "FLEX"),
    GASOLINA(3,
            "GASOLINA"),
    DIESEL(4,
            "DIESEL");

    private Integer codigo;
    private String tipo;

    @JsonCreator
    public static Combustivel verificaEnum(Integer value) {
        for (Combustivel c : Combustivel.values()) {
            if (c.getCodigo().equals(value)) {
                return c;
            }
        }
        throw new EnumvalidationException("Combustível inválido: " + value);
    }

}