package com.faculdade.aula_spring_trabalho.dto;

public record PedidoRequest(
        Long clienteId,
        Long produtoId,
        Integer quantidade
) {}
