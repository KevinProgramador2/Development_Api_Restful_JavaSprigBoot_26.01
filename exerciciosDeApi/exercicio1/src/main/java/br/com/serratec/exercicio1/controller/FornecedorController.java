package br.com.serratec.exercicio1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.exercicio1.model.Fornecedor;
import br.com.serratec.exercicio1.repository.FornecedorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/fornecedores")

public class FornecedorController {

    @Autowired
    private FornecedorRepository repository;

    @GetMapping
    public String teste() {
        return "ok";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Fornecedor inserir(@RequestBody Fornecedor fornecedor) {
        return repository.save(fornecedor);
    }

}
