package net.burnbook.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "comentario")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "publicacao_id")
    private Publicacao publicacao;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    @Column
    private Comentario comentarioPai;

    @Column(nullable = false)
    private String conteudo;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    public Comentario(Publicacao publicacao, Usuario autor, String conteudo) {
        this.publicacao = publicacao;
        this.autor = autor;
        this.conteudo = conteudo;
    }

    public Comentario(Publicacao publicacao, Comentario comentarioPai, String conteudo) {
        this.publicacao = publicacao;
        this.comentarioPai = comentarioPai;
        this.conteudo = conteudo;
    }



}
