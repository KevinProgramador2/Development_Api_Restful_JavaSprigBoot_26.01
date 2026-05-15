package br.com.serratec.atividade01.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import br.com.serratec.atividade01.repository.GerenteRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import br.com.serratec.atividade01.model.Gerente;

@RestController
@RequestMapping("/gerentes")
public class GerenteController {

    @Autowired
    private GerenteRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Gerente inserir(@Valid @RequestBody Gerente gerente) {
        return repository.save(gerente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gerente> alterarGerente(
            @Valid @RequestBody Gerente gerente,
            @PathVariable Long id) {

        if (repository.existsById(id)) {
            gerente.setId(id);
            return ResponseEntity.ok(repository.save(gerente));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gerente> buscarGerente(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Gerente>> listar() {
        return ResponseEntity.ok(repository.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
