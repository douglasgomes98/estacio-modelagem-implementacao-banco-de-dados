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
@Table(name = "professor_disciplina")
public class ProfessorDisciplina {

    @EmbeddedId
    private ProfessorDisciplinaPK id;
}
