package com.example.modelagem.entidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "instituicao")
public class Instituicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer codigo;
    private String endereco;
    private String nome;
    private Integer ci;
    private Integer igc;
    @OneToMany
    private List<Funcionario> funcionarios;
    @OneToOne
    @JoinColumn(name = "tipo_instituicao_fk")
    private TipoInstituicao tipoInstituicao;
    @OneToMany
    private List<AtoRegulatorio> atoRegulatorios;
    @OneToMany
    private List<Instalacao> instalacoes;
    @OneToMany
    private List<Aluno> alunos;
    @OneToMany
    private List<Curso> cursos;
}
