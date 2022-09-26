package com.proper.carroapi.carroapi.repository;

import com.proper.carroapi.carroapi.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroRepository extends JpaRepository<Carro, Long> {
}
