package com.faculdade.aula_spring_trabalho.controller;

import com.faculdade.aula_spring_trabalho.model.Produto;
import com.faculdade.aula_spring_trabalho.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/produto"})
public class ProdutoController {

    @Autowired
    private ProdutoRepository repo;

    @PostMapping
    public Produto create(@RequestBody Produto produto) {

        return repo.save(produto);

    }

    @DeleteMapping(path = { "/{id}" })
    public ResponseEntity<?> delete(@PathVariable long id) {

        return repo.findById(id).map(record -> {
            repo.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {

        return repo.findById(id).map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());

    }

    @GetMapping
    public List<Produto> buscaTodos() {

        return repo.findAll();

    }

}
