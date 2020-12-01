package com.example.modelagem.repositorios;

import com.example.modelagem.entidades.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargoRepositorio extends JpaRepository<Cargo, Integer> {
}
