package com.mxllo.springionic.repositories;

import com.mxllo.springionic.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {
}
