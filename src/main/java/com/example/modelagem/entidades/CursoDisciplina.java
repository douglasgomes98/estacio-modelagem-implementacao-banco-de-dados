package com.example.modelagem.entidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "curso_disciplina")
public class CursoDisciplina {

    @EmbeddedId
    private CursoDisciplinaPK id;
    @OneToOne
    @JoinColumn(name = "classificacao_curso_fk")
    private ClassificacaoCurso classificacaoCurso;

}
