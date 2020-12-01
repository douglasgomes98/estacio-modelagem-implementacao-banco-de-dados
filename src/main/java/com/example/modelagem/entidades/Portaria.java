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
@Table(name = "portaria")
public class Portaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer numero;
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "prazo_validade")
    private Date prazoValidade;
}
