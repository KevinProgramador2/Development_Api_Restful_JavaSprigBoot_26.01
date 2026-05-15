package serratec.exercicio1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import serratec.exercicio1.model.Fornecedor;
import serratec.exercicio1.repository.FornecedorRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/fornecedores")

public class FornecedorController {

    @Autowired
    private FornecedorRepository repository;

    @GetMapping
    public List<Fornecedor> listarCLientes() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fornecedor> buscarFornecedor(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Fornecedor inserir(@RequestBody Fornecedor fornecedor) {
        return repository.save(fornecedor);
    }

}
