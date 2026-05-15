package br.com.serratec.atividade01.model;

import jakarta.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Gerente extends Empregado {
    private Double adicional;
}
