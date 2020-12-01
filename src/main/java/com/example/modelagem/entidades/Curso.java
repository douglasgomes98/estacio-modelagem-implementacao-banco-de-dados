package com.example.modelagem.entidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String codigo;
    private String nome;
    @Column(name = "carga_horaria_minima")
    private Integer cargaHorariaMinima;
    @Temporal(TemporalType.TIMESTAMP)
    private Date inicio;
    private Integer enade;
    private Integer cpc;
    private Integer cc;
    @OneToOne
    @JoinColumn(name = "status_curso_fk")
    private StatusCurso statusCurso;
    @OneToOne
    @JoinColumn(name = "modalidade_curso_fk")
    private ModalidadeCurso modalidadeCurso;
    @OneToOne
    @JoinColumn(name = "grau_curso_fk")
    private GrauCurso grauCurso;
    @OneToOne
    @JoinColumn(name = "coordenador_fk")
    private Professor coordenador;
    @OneToMany
    private List<AtoRegulatorio> atoRegulatorios;
    @Column(name = "horas_atividades_complementares")
    private Integer horasAtividadesComplementares;
    @Column(name = "carga_horaria_eletivas")
    private Integer cargaHorariaEletivas;
    @Column(name = "quantidade_vaga_autorizada")
    private Integer quantidadeVagaAutorizada;
}
