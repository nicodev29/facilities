package com.correoargentino.services.postalcode.exception;

/**
 * PostalCodeNotFoundException.
 */
public class PostalCodeNotFoundException extends RuntimeException {
  public PostalCodeNotFoundException(String code) {
    super("Could not find a postal code with code " + code);
  }
}
