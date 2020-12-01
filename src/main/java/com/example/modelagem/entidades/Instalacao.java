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
@Table(name = "instalacao")
public class Instalacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String identificacao;
    @Column(name = "capacidade_alunos")
    private Integer capacidadeAlunos;
    private Integer quantidade;
    @Column(name = "area_total")
    private Integer areaTotal;
    private String descricao;
    @OneToOne
    @JoinColumn(name = "disponibilidade_fk")
    private Disponibilidade disponibilidade;
    @OneToOne
    @JoinColumn(name = "tipo_instalacao_fk")
    private TipoInstalacao tipoInstalacao;
    @OneToMany
    private List<UtilizacaoEspaco> utilizacaoEspaco;
}
