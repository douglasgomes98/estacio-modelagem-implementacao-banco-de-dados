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
@Table(name = "professor")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "experiencia_fora_magisterio_superior")
    private Integer experienciaForaMagisterioSuperior;
    @Column(name = "experencia_profissional_magisterio_superior")
    private Integer experienciaProfissionalMagisterioSuperior;
    @Column(name = "quantidade_publicacao")
    private Integer quantidadePublicacao;
    @OneToOne
    @JoinColumn(name = "funcionario_fk")
    private Funcionario funcionario;
    @OneToOne
    @JoinColumn(name = "regime_trabalho_fk")
    private RegimeTrabalho regimeTrabalho;
    @OneToOne
    @JoinColumn(name = "titulacao_fk")
    private Titulacao titulacao;
}
