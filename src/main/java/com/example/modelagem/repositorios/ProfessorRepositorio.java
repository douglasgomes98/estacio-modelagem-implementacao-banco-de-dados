package com.example.modelagem.repositorios;

import com.example.modelagem.entidades.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfessorRepositorio extends JpaRepository<Professor, Integer> {

    Optional<Professor> findByFuncionarioPessoaNome(String nome);
}
