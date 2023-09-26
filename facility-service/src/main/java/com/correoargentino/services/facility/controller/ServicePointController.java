package com.correoargentino.services.facility.controller;

import com.correoargentino.services.facility.response.ErrorResponse;
import com.correoargentino.services.facility.response.GetServicePointsResponse;
import com.correoargentino.services.facility.service.ServicePointService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.geo.Distance;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ServicePointController.
 */
@Slf4j
@Validated
@RestController
@RequestMapping(value = "v1/service-points", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Service Point")
@RequiredArgsConstructor
public class ServicePointController {
  private final ServicePointService servicePointService;

  /**
   * getServicePoints.
   */
  @Operation(
      summary = "Get service points",
      description = "Retrieves a service points.",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Successful operation",
              content = @Content(
                  schema = @Schema(implementation = GetServicePointsResponse.class),
                  mediaType = MediaType.APPLICATION_JSON_VALUE)),
          @ApiResponse(
              responseCode = "500",
              description = "Service not available",
              content = @Content(
                  schema = @Schema(implementation = ErrorResponse.class),
                  mediaType = MediaType.APPLICATION_JSON_VALUE))
      }
  )
  @GetMapping
  public ResponseEntity<GetServicePointsResponse> getServicePoints(
      @RequestParam(required = false) List<String> services,
      @RequestParam(required = false) UUID accountId) {
    String exclusion = accountId != null ? accountId.toString() : null;
    var facilities = servicePointService.findServicePoints(services, exclusion);
    return new ResponseEntity<>(
        new GetServicePointsResponse(facilities), HttpStatus.OK);
  }

  /**
   * getServicePointsByGeolocation.
   */
  @Operation(
      summary = "Get service point by geolocation",
      description = "Retrieves a facility by facility postal code.",
      parameters = {
          @Parameter(
              name = "postalCode",
              description = "The postal code of the facility to retrieve, Cannot be empty",
              required = true)
      },
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Successful operation",
              content = @Content(
                  schema = @Schema(implementation = GetServicePointsResponse.class),
                  mediaType = MediaType.APPLICATION_JSON_VALUE)),
          @ApiResponse(
              responseCode = "404",
              description = "Account not found",
              content = @Content(
                  schema = @Schema(implementation = ErrorResponse.class),
                  mediaType = MediaType.APPLICATION_JSON_VALUE)),
          @ApiResponse(
              responseCode = "500",
              description = "Service not available",
              content = @Content(
                  schema = @Schema(implementation = ErrorResponse.class),
                  mediaType = MediaType.APPLICATION_JSON_VALUE))
      }
  )
  @GetMapping(value = "/geolocation")
  public ResponseEntity<GetServicePointsResponse> getServicePointsByGeolocation(
      @NotNull @RequestParam Double latitude, @NotNull @RequestParam Double longitude,
      @RequestParam(defaultValue = "5000") Integer radius,
      @RequestParam(required = false) List<String> services,
      @RequestParam(required = false) UUID accountId) {
    String exclusion = accountId != null ? accountId.toString() : null;
    var facilities = servicePointService
        .findServicePointsByGeolocation(latitude,
            longitude, new Distance(radius), services, exclusion);
    return new ResponseEntity<>(
        new GetServicePointsResponse(facilities), HttpStatus.OK);
  }

  /**
   * getServicePointsByProvince.
   */
  @Operation(
      summary = "Get service point by province",
      description = "Retrieves a service point by service point province.",
      parameters = {
          @Parameter(
              name = "provinceCode",
              description = "The province code of the service point to retrieve, Cannot be empty",
              required = true)
      },
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Successful operation",
              content = @Content(
                  schema = @Schema(implementation = GetServicePointsResponse.class),
                  mediaType = MediaType.APPLICATION_JSON_VALUE)),
          @ApiResponse(
              responseCode = "404",
              description = "Account not found",
              content = @Content(
                  schema = @Schema(implementation = ErrorResponse.class),
                  mediaType = MediaType.APPLICATION_JSON_VALUE)),
          @ApiResponse(
              responseCode = "500",
              description = "Service not available",
              content = @Content(
                  schema = @Schema(implementation = ErrorResponse.class),
                  mediaType = MediaType.APPLICATION_JSON_VALUE))
      }
  )
  @GetMapping(value = "/province/{provinceCode}")
  public ResponseEntity<GetServicePointsResponse> getServicePointsByProvinceCode(
      @NotNull @PathVariable String provinceCode,
      @RequestParam(required = false) List<String> services,
      @RequestParam(required = false) UUID accountId) {
    String exclusion = accountId != null ? accountId.toString() : null;
    var facilities = servicePointService
        .findServicePointsByRegion(provinceCode, services, exclusion);
    return new ResponseEntity<>(
        new GetServicePointsResponse(facilities), HttpStatus.OK);
  }
}
