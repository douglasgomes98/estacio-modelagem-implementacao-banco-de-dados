package com.example.modelagem.entidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class CursoDisciplinaPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "curso_fk")
    private Curso curso;
    @ManyToOne
    @JoinColumn(name = "disciplina_fk")
    private Disciplina disciplina;
}
