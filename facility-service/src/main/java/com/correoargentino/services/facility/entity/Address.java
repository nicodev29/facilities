package com.correoargentino.services.facility.entity;

import com.correoargentino.services.facility.util.GeoJsonPointSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;

/**
 * Address.
 */
@Data
@AllArgsConstructor
public class Address {
  @NotBlank
  private String addressLine1;
  private String addressLine2;
  @NotBlank
  private String city;
  @Indexed
  @NotBlank
  @Size(max = 6)
  private String region;
  @Indexed
  @NotNull
  private String postalCode;
  @NotBlank
  @Size(max = 2)
  private String country;
  @JsonSerialize(using = GeoJsonPointSerializer.class)
  @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
  private GeoJsonPoint location;
}
