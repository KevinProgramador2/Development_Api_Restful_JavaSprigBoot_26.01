package br.com.serratec.atividade01.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SetorException extends RuntimeException {

    public SetorException(String message) {
        super(message);
    }

}
