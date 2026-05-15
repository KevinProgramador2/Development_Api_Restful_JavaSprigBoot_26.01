package br.com.serratec.aula5.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.aula5.model.Manuntencao;
import br.com.serratec.aula5.repository.ManuntencaoRepository;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/manuntencoes")
public class ManuntencaoController {

    @Autowired

    private ManuntencaoRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Manuntencao inserir(@Valid @RequestBody Manuntencao manuntencao) {
        return repository.save(manuntencao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Manuntencao> alterarManuntencao(@Valid @RequestBody Manuntencao manuntencao,
            @PathVariable Long id) {
        if (repository.existsById(id)) {
            manuntencao.setId(id);
            return ResponseEntity.ok(repository.save(manuntencao));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manuntencao> buscarManuntencao(@Valid @PathVariable Long id) {
        Optional<Manuntencao> manuntencaoOptional = repository.findById(id);

        if (manuntencaoOptional.isPresent()) {
            return ResponseEntity.ok(manuntencaoOptional.get());
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