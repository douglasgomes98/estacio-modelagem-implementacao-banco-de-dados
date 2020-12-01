package com.example.modelagem.repositorios;

import com.example.modelagem.entidades.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaRepositorio extends JpaRepository<Pessoa, Integer> {

    Optional<Pessoa> findByNome(String nome);
}
