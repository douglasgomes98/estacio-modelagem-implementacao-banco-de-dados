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
@Table(name = "ato_regulatorio")
public class AtoRegulatorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    @JoinColumn(name = "tipo_ato_regulatorio_fk")
    private TipoAtoRegulatorio tipoAtoRegulatorio;
    @OneToOne
    @JoinColumn(name = "portaria_fk")
    private Portaria portaria;
}
