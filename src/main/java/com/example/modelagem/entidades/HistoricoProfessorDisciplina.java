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
@Table(name = "historico_professor_disciplina")
public class HistoricoProfessorDisciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer professor;
    private Integer disciplina;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAlocacao;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataDesalocado;
}
