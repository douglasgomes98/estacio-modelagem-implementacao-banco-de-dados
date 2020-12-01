package com.example.modelagem.repositorios;

import com.example.modelagem.entidades.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepositorio extends JpaRepository<Livro, Integer> {
}
