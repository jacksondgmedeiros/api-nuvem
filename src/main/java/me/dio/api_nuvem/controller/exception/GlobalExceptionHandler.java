package me.dio.api_nuvem.controller.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
@Component
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // adicionei o construtor pois estava dando erro, mas pode testar, usei uma versão anterior do spring e foi assim que
    // deu certo, a versão está no pom
    public GlobalExceptionHandler() {
    }


    // Vê todos os erros genericos que der e lança um erro 500
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<String> handleInesperada(Throwable erroInesperado, HttpServletRequest request) {

        var path = request.getRequestURI();

        // Ignora erros do Swagger
        if (path.contains("/v3/api-docs") || path.contains("/swagger-ui")) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno, mas Swagger não deve ser afetado");
        }

        var message = "Erro inesperado, verifique o log";
        logger.error(message, erroInesperado);

        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // a anotação é o tratamento, ou seja, quando der um IllegalArgumentException
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleExists(IllegalArgumentException businessException) {
        // o retorno é a mensagem que foi tratada lá na service (conta já existe) e o status dessa exceção é 422
        return new ResponseEntity<>(businessException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    // o retorno é a mensagem que eu quiser escolher e o status dessa excessão é 204
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handle(NoSuchElementException businessException) {
        return new ResponseEntity<>(businessException.getMessage(), HttpStatus.NO_CONTENT);
    }

}
