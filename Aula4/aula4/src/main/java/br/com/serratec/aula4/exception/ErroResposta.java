package br.com.serratec.aula4.exception;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // get e set
@NoArgsConstructor // constructo vazio
@AllArgsConstructor // contructor cheio

public class ErroResposta {
    private Integer status;
    private String titulo;
    private LocalDateTime dataHora;
    private List<String> erros;

}
