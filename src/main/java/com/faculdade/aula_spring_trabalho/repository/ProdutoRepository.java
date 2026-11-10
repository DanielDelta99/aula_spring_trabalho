package com.faculdade.aula_spring_trabalho.repository;

import com.faculdade.aula_spring_trabalho.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
