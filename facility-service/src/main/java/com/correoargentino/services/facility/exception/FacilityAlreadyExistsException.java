package com.correoargentino.services.facility.exception;

/**
 * FacilityAlreadyExistsException.
 */
public class FacilityAlreadyExistsException extends RuntimeException {
  public FacilityAlreadyExistsException(String code) {
    super("A facility with the code " + code + " already exists");
  }
}
