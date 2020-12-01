package com.example.modelagem.repositorios;

import com.example.modelagem.entidades.ClassificacaoCurso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClassificacaoCursoRepositorio extends JpaRepository<ClassificacaoCurso, Integer> {

    Optional<ClassificacaoCurso> findByNome(String nome);
}
