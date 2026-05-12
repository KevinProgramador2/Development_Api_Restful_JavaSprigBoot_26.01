package br.com.serratec.aula3.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Double preco;
    private String descricao;
    private LocalDate dataCadastro;

    @JsonBackReference // referencia o mandante
    @ManyToOne // relacionamento de categoria em produtos
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @Override
    public String toString() {
        return "Produto [id=" + id + ", preco=" + preco + ", descricao=" + descricao + ", dataCadastro=" + dataCadastro
                + "]";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

}
