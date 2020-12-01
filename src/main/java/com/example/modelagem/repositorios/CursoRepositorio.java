package com.example.modelagem.repositorios;

import com.example.modelagem.entidades.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepositorio extends JpaRepository<Curso, Integer> {
}
