package com.correoargentino.services.facility.service;

import com.correoargentino.services.facility.entity.Facility;
import java.util.List;
import org.springframework.data.geo.Distance;
import org.springframework.lang.Nullable;

/**
 * ServicePointService.
 */
public interface ServicePointService {
  List<Facility> findServicePoints(@Nullable List<String> services, @Nullable String exclusion);

  List<Facility> findServicePointsByGeolocation(
      Double latitude, Double longitude, Distance distance,
      @Nullable List<String> services, @Nullable String exclusion);

  List<Facility> findServicePointsByRegion(
      String region, @Nullable List<String> services, @Nullable String exclusion);
}
