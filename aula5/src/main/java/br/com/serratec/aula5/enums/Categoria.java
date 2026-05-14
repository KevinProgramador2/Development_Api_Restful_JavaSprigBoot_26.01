package br.com.serratec.aula5.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.serratec.aula5.exceptions.EnumvalidationException;

public enum Categoria {
    HATCH, SEDAN, SUV, PICAPE, CONVERSIVEL, ESPORTIVO, COUPE;

    @JsonCreator
    public static Combustivel verificaEnum(String value) {
        for (Combustivel c : Combustivel.values()) {
            if (c.name().equalsIgnoreCase(value)) {
                return c;
            }
        }
        throw new EnumvalidationException("Combustível inválido: " + value);
    }

}