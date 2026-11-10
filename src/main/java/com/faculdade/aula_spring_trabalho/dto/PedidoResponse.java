package com.faculdade.aula_spring_trabalho.dto;

public record PedidoResponse(
        Long id,
        String nomeCliente,
        String nomeProduto,
        Integer quantidade
) {}
