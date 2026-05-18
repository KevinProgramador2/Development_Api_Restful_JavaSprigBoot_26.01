package br.com.serratec.trabalhovalendonota01.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.trabalhovalendonota01.model.Medico;
import br.com.serratec.trabalhovalendonota01.repository.MedicoRepository;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired

    private MedicoRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Medico inserir(@Valid @RequestBody Medico medico) {
        return repository.save(medico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medico> alterarMedico(
            @Valid @RequestBody Medico medico,
            @PathVariable Long id) {
        if (repository.existsById(id)) {
            medico.setId(id);
            return ResponseEntity.ok(repository.save(medico));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> buscarMedico(
            @Valid @PathVariable Long id) {
        Optional<Medico> medicoOptional = repository.findById(id);

        if (medicoOptional.isPresent()) {
            
            return ResponseEntity.ok(medicoOptional.get());
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