package com.betrybe.museumfinder.exception;

/**
 * Exception MuseumNotFoundException.
 */
public class MuseumNotFoundException extends RuntimeException {
  public MuseumNotFoundException(String message) {
    super(message);
  }
}
