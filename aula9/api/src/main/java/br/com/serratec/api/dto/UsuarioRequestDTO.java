package br.com.serratec.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UsuarioRequestDTO {

    
    private String nome;
    private String senha;
    private String email;

}
