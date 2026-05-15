package br.com.serratec.aula4.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Preencha a descrição")
    @Size(min = 5, max = 40, message = "Descrição incorreta")
    private String descricao;
    @DecimalMax(value = "1000", message = "value max. de produto inválido")
    @DecimalMin(value = "100", message = "valor min. ddo produtos invalidos")
    private Double preco;

    @PastOrPresent(message = "Date de cadastro incorreto")
    private LocalDate dataCadastro;

    @JsonBackReference // referencia o mandante
    @ManyToOne // relacionamento de categoria em produtos
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

}
