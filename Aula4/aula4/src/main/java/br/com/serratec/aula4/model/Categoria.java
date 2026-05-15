package br.com.serratec.aula4.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // “O BANCO DE DADOS é responsável por gerar o ID
                                                        // automaticamente, usando auto-incremento”
    private Long id;

    @NotBlank(message = "Preencha a categoria")
    private String nome;

    @JsonManagedReference
    @OneToMany(mappedBy = "categoria") // MAPEIA PELA CATEGORIA
    private List<Produto> produto;

}
