package com.example.modelagem.repositorios;

import com.example.modelagem.entidades.ProfessorDisciplina;
import com.example.modelagem.entidades.ProfessorDisciplinaPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorDisciplinaRepositorio extends JpaRepository<ProfessorDisciplina, ProfessorDisciplinaPK> {
}
