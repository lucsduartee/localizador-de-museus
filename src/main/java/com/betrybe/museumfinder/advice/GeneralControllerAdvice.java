package com.betrybe.museumfinder.advice;

import com.betrybe.museumfinder.exception.InvalidCoordinateException;
import com.betrybe.museumfinder.exception.MuseumNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * ControllerAdvice GeneralControllerAdvice.
 */
@ControllerAdvice
public class GeneralControllerAdvice {
  /**
   * Handles MuseumNotFoundException.
   *
   * @param exception an exception
   * @return status ad message
   */
  @ExceptionHandler(MuseumNotFoundException.class)
  public ResponseEntity<String> handleMuseumNotFoundException(
          MuseumNotFoundException exception) {
    return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body("Museu não encontrado!");
  }

  /**
   * Handles InvalidCoordinateException.
   *
   * @param exception an exception
   * @return status ad message
   */
  @ExceptionHandler(InvalidCoordinateException.class)
  public ResponseEntity<String> handleInvalidCoordinateException(
          InvalidCoordinateException exception) {
    return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body("Coordenada inválida!");
  }

  /**
   * Handles handleException.
   *
   * @param exception an exception
   * @return status ad message
   */
  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<String> handleException(RuntimeException exception) {
    return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Erro interno!");
  }
}
