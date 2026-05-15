package br.com.serratec.atividade01.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.serratec.atividade01.exceptions.SetorException;

public enum SetorEnums {

    MALHARIA, CORTE, BORDADO, TINTARIA, ADMINISTRACAO, ALMOXARIFADO;

    @JsonCreator
    public static SetorEnums verificaEnum(String value) {
        for (SetorEnums s : SetorEnums.values()) {
            if (s.name().equalsIgnoreCase(value)) {
                return s;
            }
        }
        throw new SetorException("Setor inválido: " + value);
    }
}
