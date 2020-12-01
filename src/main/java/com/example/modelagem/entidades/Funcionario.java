package com.example.modelagem.entidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "funcionario")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String matricula;
    @Temporal(TemporalType.TIMESTAMP)
    private Date contratacao;
    @JoinColumn(name = "pessoa_fk")
    @OneToOne
    private Pessoa pessoa;
    @JoinColumn(name = "cargo_fk")
    @OneToOne
    private Cargo cargo;
    @JoinColumn(name = "curso_fk")
    @OneToOne
    private Curso curso;
}
