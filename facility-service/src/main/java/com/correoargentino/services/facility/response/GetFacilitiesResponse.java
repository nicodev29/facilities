package com.correoargentino.services.facility.response;

import com.correoargentino.services.facility.entity.Facility;
import java.util.List;

/**
 * GetFacilitiesResponse.
 */
public record GetFacilitiesResponse(
    List<Facility> facilities,
    Integer currentPage,
    Long totalItems,
    Integer totalPages) {
}
