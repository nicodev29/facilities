package com.correoargentino.services.facility.mapper;

import com.correoargentino.services.facility.entity.Facility;
import com.correoargentino.services.facility.request.CreateFacilityRequest;
import com.correoargentino.services.facility.request.UpdateFacilityRequest;
import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * FacilityMapper.
 */
@Mapper(componentModel = "spring", imports = {UUID.class, Facility.Status.class})
public interface FacilityMapper {
  @Mapping(target = "id", expression = "java(UUID.randomUUID())")
  @Mapping(target = "exclusions", ignore = true)
  @Mapping(target = "status", expression = "java(Facility.Status.ACTIVE)")
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  Facility requestToFacility(CreateFacilityRequest request);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "exclusions", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  void updateFacilityFromRequest(@MappingTarget Facility facility, UpdateFacilityRequest request);
}
