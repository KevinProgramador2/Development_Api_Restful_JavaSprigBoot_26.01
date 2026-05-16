package br.com.serratec.aula5.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.aula5.model.Servico;
import br.com.serratec.aula5.repository.ServicoRepository;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

    @Autowired

    private ServicoRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Servico inserir(@Valid @RequestBody Servico servico) {
        return repository.save(servico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servico> alterarServico(@Valid @RequestBody Servico servico,
            @PathVariable Long id) {
        if (repository.existsById(id)) {
            servico.setId(id);
            return ResponseEntity.ok(repository.save(servico));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servico> buscarServico(@Valid @PathVariable Long id) {
        Optional<Servico> servicoOptional = repository.findById(id);

        if (servicoOptional.isPresent()) {
            return ResponseEntity.ok(servicoOptional.get());
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