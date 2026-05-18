package br.com.serratec.trabalhovalendonota01.controller;


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
import br.com.serratec.trabalhovalendonota01.service.ConsultaService;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Consulta inserir(@Valid @RequestBody Consulta consulta) {
        return service.salvar(consulta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consulta> alterarConsulta(
            @Valid @RequestBody Consulta consulta,
            @PathVariable Long id) {

        if (service.existe(id)) {
            consulta.setId(id);
            return ResponseEntity.ok(service.salvar(consulta));
        }
        return ResponseEntity.notFound().build();
    }

      @GetMapping("/{id}")
    public ResponseEntity<Consulta> buscarConsulta(@PathVariable Long id) {
        return service.buscar(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable Long id) {
        if (service.existe(id)) {
            service.deletar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
