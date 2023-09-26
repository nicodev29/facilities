package com.correoargentino.services.facility.response;

import com.correoargentino.services.facility.entity.Facility;
import java.util.List;

/**
 * GetFacilitiesByPostalCode.
 */
public record GetServicePointsResponse(List<Facility> servicePoints) {
}
