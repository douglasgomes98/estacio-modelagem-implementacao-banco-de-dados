package com.example.modelagem.repositorios;

import com.example.modelagem.entidades.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepositorio extends JpaRepository<Aluno, Integer> {
}
