package net.burnbook.app.infra.exception.model;

public class AcessoNegadoException extends RuntimeException{
    public AcessoNegadoException(String mensagem) {
        super(mensagem);
    }
}
