package com.faculdade.aula_spring_trabalho.controller;

import com.faculdade.aula_spring_trabalho.dto.PedidoRequest;
import com.faculdade.aula_spring_trabalho.dto.PedidoResponse;
import com.faculdade.aula_spring_trabalho.model.Cliente;
import com.faculdade.aula_spring_trabalho.model.Pedido;
import com.faculdade.aula_spring_trabalho.model.Produto;
import com.faculdade.aula_spring_trabalho.repository.ClienteRepository;
import com.faculdade.aula_spring_trabalho.repository.PedidoRepository;
import com.faculdade.aula_spring_trabalho.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping({"/pedido"})
public class PedidoController {

    @Autowired
    private PedidoRepository repoPedido;
    @Autowired
    private ClienteRepository repoCliente;
    @Autowired
    private ProdutoRepository repoProduto;


    @PostMapping
    public ResponseEntity<PedidoResponse> create(@RequestBody PedidoRequest request) {

        Cliente cliente = repoCliente.findById(request.clienteId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
        Produto produto = repoProduto.findById(request.produtoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setProduto(produto);
        pedido.setQuantidade(request.quantidade());

        Pedido salvo = repoPedido.save(pedido);

        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(salvo));
    }

    @DeleteMapping(path = { "/{id}" })
    public ResponseEntity<?> delete(@PathVariable long id) {

        return repoPedido.findById(id).map(record -> {
            repoPedido.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());

    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponse> findById(@PathVariable Long id) {
        return repoPedido.findById(id)
                .map(this::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<PedidoResponse> buscaTodos() {
        return repoPedido.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private PedidoResponse toResponse(Pedido pedido) {
        return new PedidoResponse(
                pedido.getId(),
                pedido.getCliente().getNome(),
                pedido.getProduto().getNome(),
                pedido.getQuantidade()
        );
    }

}
