package net.burnbook.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "curtida")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Curtida {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "publicacao_id")
    private Publicacao publicacao;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Curtida(Publicacao publicacao, Usuario usuario) {
        this.publicacao = publicacao;
        this.usuario = usuario;
    }

    public Curtida(Publicacao publicacao) {
        this.publicacao = publicacao;
    }
}
