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
@Table(name = "disciplina")
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String codigo;
    private String ementa;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Livro> livros;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Disciplina> Equivalente;
    @OneToOne
    private ModalidadeDisciplina modalidadeDisciplina;
    @Column(name = "periodo_minimo")
    private Integer periodoMinimo;
    @Column(name = "carga_horaria")
    private Integer cargaHoraria;
    @Column(name = "quantidade_vaga_autorizada")
    private Integer quantidadeVagaAutorizada;
}
