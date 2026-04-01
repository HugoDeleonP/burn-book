package net.burnbook.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "usuario")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column (nullable = false)
    private String nome;

    @Column (name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column (nullable = false)
    private String email;

    @Column (nullable = false)
    private String senha;

    @Column (nullable = false)
    private String cpf;

    @Column(name = "foto_perfil_url")
    private String fotoPerfilUrl;

    public Usuario(String username, String nome, LocalDate dataNascimento, String email, String senha, String cpf, String fotoPerfilUrl) {
        this.username = username;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.fotoPerfilUrl = fotoPerfilUrl;
    }
}
