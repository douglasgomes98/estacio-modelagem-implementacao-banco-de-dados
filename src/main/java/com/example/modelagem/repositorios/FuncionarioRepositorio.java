package com.example.modelagem.repositorios;

import com.example.modelagem.entidades.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepositorio extends JpaRepository<Funcionario, Integer> {
}
