package br.com.serratec.trabalhovalendonota01.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.trabalhovalendonota01.model.Consulta;
import br.com.serratec.trabalhovalendonota01.model.Paciente;
import br.com.serratec.trabalhovalendonota01.repository.PacienteRepository;
// import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired

    private PacienteRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Paciente inserir(@Valid @RequestBody Paciente paciente) {
        for (Consulta c : paciente.getConsultas()) {
            c.setPaciente(paciente);
        }

        return repository.save(paciente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> alterarPaciente(
            @Valid @RequestBody Paciente paciente,
            @PathVariable Long id) {
        if (repository.existsById(id)) {
            paciente.setId(id);
            for (Consulta c : paciente.getConsultas()) {
                c.setPaciente(paciente);
            }
            return ResponseEntity.ok(repository.save(paciente));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPaciente(
            @Valid @PathVariable Long id) {
        Optional<Paciente> pacienteOptional = repository.findById(id);

        if (pacienteOptional.isPresent()) {
            return ResponseEntity.ok(pacienteOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<Paciente> listarPacientes() {

        return repository.findAll();

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
