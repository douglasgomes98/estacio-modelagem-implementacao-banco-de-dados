package com.example.modelagem.repositorios;

import com.example.modelagem.entidades.Instituicao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstituicaoRepositorio extends JpaRepository<Instituicao, Integer> {
}
