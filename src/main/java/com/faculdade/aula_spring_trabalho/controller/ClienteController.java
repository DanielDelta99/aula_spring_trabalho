package com.faculdade.aula_spring_trabalho.controller;

import com.faculdade.aula_spring_trabalho.model.Cliente;
import com.faculdade.aula_spring_trabalho.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/cliente"})
public class ClienteController {

    @Autowired
    private ClienteRepository repo;

    @PostMapping
    public Cliente create(@RequestBody Cliente cliente) {

        return repo.save(cliente);

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
    public List<Cliente> buscaTodos() {

        return repo.findAll();

    }

}
