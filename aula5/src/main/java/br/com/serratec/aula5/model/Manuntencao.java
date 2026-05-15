package br.com.serratec.aula5.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Manuntencao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;
    private String descricao;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_veiculo")
    private Veiculo veiculo;

    /**
     * Este método é um gatilho automático.
     * Ele é executado pelo Hibernate logo antes de criar um novo registo no banco
     * de dados.
     */
    @PrePersist
    public void antesDeSalvar() {
        // Pega a data de hoje do relógio do sistema e guarda na variável
        dataEntrada = LocalDate.now();
    }
}
