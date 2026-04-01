package net.burnbook.app.infra.exception;

import net.burnbook.app.infra.exception.dto.ErroDTOResponse;
import net.burnbook.app.infra.exception.model.AcessoNegadoException;
import net.burnbook.app.infra.exception.model.ConflitoException;
import net.burnbook.app.infra.exception.model.EntidadeNaoEncontradaException;
import net.burnbook.app.infra.exception.model.RegraDeNegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<ErroDTOResponse> handleNaoEncontrada(EntidadeNaoEncontradaException ex) {
        return build(HttpStatus.NOT_FOUND, "Não Encontrado", ex.getMessage());
    }

    @ExceptionHandler(AcessoNegadoException.class)
    public ResponseEntity<ErroDTOResponse> handleAcessoNegado(AcessoNegadoException ex) {
        return build(HttpStatus.FORBIDDEN, "Acesso Negado", ex.getMessage());
    }

    @ExceptionHandler(ConflitoException.class)
    public ResponseEntity<ErroDTOResponse> handleConflito(ConflitoException ex) {
        return build(HttpStatus.CONFLICT, "Conflito", ex.getMessage());
    }

    @ExceptionHandler(RegraDeNegocioException.class)
    public ResponseEntity<ErroDTOResponse> handleRegraDeNegocio(RegraDeNegocioException ex) {
        return build(HttpStatus.BAD_REQUEST, "Requisição Inválida", ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroDTOResponse> handleValidacao(MethodArgumentNotValidException ex) {
        String mensagem = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(f -> f.getField() + ": " + f.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return build(HttpStatus.BAD_REQUEST, "Erro de Validação", mensagem);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErroDTOResponse> handleCredenciaisInvalidas(BadCredentialsException ex) {
        return build(HttpStatus.UNAUTHORIZED, "Não Autorizado", "Email ou senha inválidos");
    }

    private ResponseEntity<ErroDTOResponse> build(HttpStatus status, String erro, String mensagem) {
        return ResponseEntity
                .status(status)
                .body(new ErroDTOResponse(status.value(), erro, mensagem, LocalDateTime.now()));
    }

}
