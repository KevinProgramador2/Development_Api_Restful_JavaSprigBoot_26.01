package br.com.serratec.trabalhovalendonota01.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.trabalhovalendonota01.model.Consulta;
import br.com.serratec.trabalhovalendonota01.repository.ConsultaRepository;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired

    private ConsultaRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Consulta inserir(@Valid @RequestBody Consulta consulta) {
        return repository.save(consulta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consulta> alterarConsulta(
            @Valid @RequestBody Consulta consulta,
            @PathVariable Long id) {
        if (repository.existsById(id)) {
            consulta.setId(id);
            return ResponseEntity.ok(repository.save(consulta));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> buscarConsulta(
            @Valid @PathVariable Long id) {
        Optional<Consulta> consultaOptional = repository.findById(id);

        if (consultaOptional.isPresent()) {
            return ResponseEntity.ok(consultaOptional.get());
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
