package com.example.modelagem.repositorios;

import com.example.modelagem.entidades.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DisciplinaRepositorio extends JpaRepository<Disciplina, Integer> {

    Optional<Disciplina> findByCodigo(String codigo);
}
