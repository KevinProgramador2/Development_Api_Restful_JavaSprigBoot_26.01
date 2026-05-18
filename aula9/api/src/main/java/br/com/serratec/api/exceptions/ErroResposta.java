package br.com.serratec.api.exceptions;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
// import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor

public class ErroResposta {
    private Integer status;
    private String titulo;
    private LocalDateTime dataHora;
    private List<String> erros;

    public ErroResposta(Integer status, String titulo, LocalDateTime dataHora) {
        this.status = status;
        this.titulo = titulo;
        this.dataHora = dataHora;
    }

}