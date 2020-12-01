package com.example.modelagem.repositorios;

import com.example.modelagem.entidades.CursoDisciplina;
import com.example.modelagem.entidades.CursoDisciplinaPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoDisciplinaRepositorio extends JpaRepository<CursoDisciplina, CursoDisciplinaPK> {
}
