package com.faculdade.aula_spring_trabalho.repository;

import com.faculdade.aula_spring_trabalho.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
