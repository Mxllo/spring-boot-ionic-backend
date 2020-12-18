package com.mxllo.springionic.repositories;

import com.mxllo.springionic.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {
}
