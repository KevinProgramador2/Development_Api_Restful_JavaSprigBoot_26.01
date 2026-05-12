package br.com.serratec.aula3.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.aula3.model.Produto;
import br.com.serratec.aula3.repository.ProdutoRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @PostMapping
    public Produto inserir(@RequestBody Produto produto) {

        return repository.save(produto);
    }

    @GetMapping

    public List<Produto> listar() {
        return repository.findAll();
    }

    @PutMapping("{id}")
    public ResponseEntity<Produto> alterarProdutos(@RequestBody Produto produto, @PathVariable Long id) {
        Optional<Produto> produtosOptional = repository.findById(id);
        if (produtosOptional.isPresent()) {
            produto.setId(id);
            return ResponseEntity.ok(repository.save(produto));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirProduto(@PathVariable Long id) {

        Optional<Produto> produtoOptional = repository.findById(id);

        if (produtoOptional.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
