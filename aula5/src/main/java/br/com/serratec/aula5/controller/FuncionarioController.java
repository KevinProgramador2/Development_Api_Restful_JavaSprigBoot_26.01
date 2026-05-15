package br.com.serratec.aula5.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.aula5.model.Funcionario;
import br.com.serratec.aula5.repository.FuncionarioRepository;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired

    private FuncionarioRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Funcionario inserir(@Valid @RequestBody Funcionario funcionario) {
        return repository.save(funcionario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> alterarFuncionario(@Valid @RequestBody Funcionario funcionario,
            @PathVariable Long id) {
        if (repository.existsById(id)) {
            funcionario.setId(id);
            return ResponseEntity.ok(repository.save(funcionario));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> buscarFuncionario(@Valid @PathVariable Long id) {
        Optional<Funcionario> funcionarioOptional = repository.findById(id);

        if (funcionarioOptional.isPresent()) {
            return ResponseEntity.ok(funcionarioOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@Valid @PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}