package com.correoargentino.services.facility.exception;

import java.util.UUID;

/**
 * FacilityNotFoundException.
 */
public class FacilityNotFoundException extends RuntimeException {
  public FacilityNotFoundException(UUID id) {
    super("Could not find a facility with id " + id.toString());
  }
}
