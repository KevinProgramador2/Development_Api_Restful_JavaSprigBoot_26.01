package br.com.serratec.aula5.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EnumvalidationException extends RuntimeException {

    public EnumvalidationException(String message) {
        super(message);
    }
}
