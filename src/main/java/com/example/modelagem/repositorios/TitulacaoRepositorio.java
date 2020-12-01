package com.example.modelagem.repositorios;

import com.example.modelagem.entidades.Aluno;
import com.example.modelagem.entidades.Titulacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TitulacaoRepositorio extends JpaRepository<Titulacao, Integer> {

    Optional<Titulacao> findByNome(String nome);
}
