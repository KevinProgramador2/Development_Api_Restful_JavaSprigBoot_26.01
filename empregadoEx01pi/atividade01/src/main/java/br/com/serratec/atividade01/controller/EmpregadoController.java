package br.com.serratec.atividade01.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.atividade01.model.Empregado;
import br.com.serratec.atividade01.repository.EmpregadoRepository;
import jakarta.validation.Valid;

@Controller
@RestController
@RequestMapping("/empregados")
public class EmpregadoController {

    @Autowired

    private EmpregadoRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Empregado inserir(@Valid @RequestBody Empregado empregado) {
        return repository.save(empregado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empregado> alterarEmpregado(@Valid @RequestBody Empregado empregado,
            @PathVariable Long id) {
        if (repository.existsById(id)) {
            empregado.setId(id);
            return ResponseEntity.ok(repository.save(empregado));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empregado> buscarEmpregado(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Empregado>> listar() {
        return ResponseEntity.ok(repository.findAll());
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
