package com.correoargentino.services.facility.service;

import com.correoargentino.services.facility.entity.Facility;
import com.correoargentino.services.facility.request.CreateFacilityRequest;
import com.correoargentino.services.facility.request.UpdateFacilityRequest;
import java.util.UUID;
import org.springframework.data.domain.Page;

/**
 * FacilityService.
 */
public interface FacilityService {
  UUID createFacility(CreateFacilityRequest request);

  Facility getFacility(UUID id);

  void updateFacility(UUID id, UpdateFacilityRequest request);

  void deleteFacility(UUID id);

  void activateFacility(UUID id);

  void deactivateFacility(UUID id);

  void closeFacility(UUID id);

  Page<Facility> findFacilities(
      Facility.Type type, Facility.Status status, Integer page, Integer size);
}
