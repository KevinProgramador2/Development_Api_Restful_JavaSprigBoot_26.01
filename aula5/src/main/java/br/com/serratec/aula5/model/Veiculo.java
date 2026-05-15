package br.com.serratec.aula5.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 7, min = 7, message = "Preencha a placa com 7 caracteres no maximo corretamente")
    private String placa;

    @NotBlank(message = "Preencha a marca")
    private String marca;

    @NotBlank(message = "Preencha o modelo")
    private String modelo;

    @Embedded
    private Caracteristica caracteristica;

    @OneToOne
    @JoinColumn(name = "id_funcionario", unique = true)
    private Funcionario funcionario;

}
