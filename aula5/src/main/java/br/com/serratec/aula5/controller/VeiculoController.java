package br.com.serratec.aula5.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.aula5.model.Veiculo;
import br.com.serratec.aula5.repository.VeiculoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoRepository repository;

    @Operation(summary = "Inserir um veículo", description = "A resposta retorna dados do veículo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = Veiculo.class), mediaType = "application/json") }, description = "Cadastra veículo"),
            @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
            @ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Veiculo inserir(@Valid @RequestBody Veiculo veiculo) {

        return repository.save(veiculo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Veiculo> alterarVeiculo(@Valid @RequestBody Veiculo veiculo, @PathVariable Long id) {
        if (repository.existsById(id)) {
            veiculo.setId(id);
            return ResponseEntity.ok(repository.save(veiculo));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> buscarVeiculo(@Valid @PathVariable Long id) {
        Optional<Veiculo> veiculoOptional = repository.findById(id);
        if (veiculoOptional.isPresent()) {
            ResponseEntity.ok(veiculoOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> apagar(@Valid @PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
