package com.example.modelagem.repositorios;

import com.example.modelagem.entidades.Disponibilidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisponibilidadeRepositorio extends JpaRepository<Disponibilidade, Integer> {
}
