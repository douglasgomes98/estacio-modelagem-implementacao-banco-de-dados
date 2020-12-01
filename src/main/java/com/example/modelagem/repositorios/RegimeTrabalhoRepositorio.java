package com.example.modelagem.repositorios;

import com.example.modelagem.entidades.RegimeTrabalho;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegimeTrabalhoRepositorio extends JpaRepository<RegimeTrabalho, Integer> {

    Optional<RegimeTrabalho> findByNome(String nome);
}
