package net.burnbook.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "publicacao")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Publicacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    @Column(nullable = false)
    private String conteudo;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @Column(nullable = false)
    private Boolean isAnonimo;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    public Publicacao(String conteudo, Categoria categoria, Boolean isAnonimo) {
        this.conteudo = conteudo;
        this.categoria = categoria;
        this.isAnonimo = isAnonimo;
    }
}
