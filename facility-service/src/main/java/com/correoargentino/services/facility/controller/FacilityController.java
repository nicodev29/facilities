package com.correoargentino.services.facility.controller;

import com.correoargentino.services.facility.entity.Facility;
import com.correoargentino.services.facility.request.CreateFacilityRequest;
import com.correoargentino.services.facility.request.UpdateFacilityRequest;
import com.correoargentino.services.facility.response.CreateFacilityResponse;
import com.correoargentino.services.facility.response.ErrorResponse;
import com.correoargentino.services.facility.response.GetFacilitiesResponse;
import com.correoargentino.services.facility.response.GetFacilityResponse;
import com.correoargentino.services.facility.service.FacilityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.Set;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * FacilityController.
 */
@Slf4j
@Validated
@RestController
@RequestMapping(value = "v1/facilities", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Facilities")
@RequiredArgsConstructor
public class FacilityController {
  private final FacilityService facilityService;

  /**
   * createFacility.
   */
  @Operation(
      summary = "Create a Facility",
      description = "Create a new facility.",
      responses = {
          @ApiResponse(
              responseCode = "201",
              description = "Successful operation",
              content = @Content(
                  schema = @Schema(implementation = CreateFacilityResponse.class),
                  mediaType = MediaType.APPLICATION_JSON_VALUE)),
          @ApiResponse(
              responseCode = "400",
              description = "Facility already exists",
              content = @Content(
                  schema = @Schema(implementation = ErrorResponse.class),
                  mediaType = MediaType.APPLICATION_JSON_VALUE)),
          @ApiResponse(
              responseCode = "409",
              description = "Bad request",
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
  @PostMapping
  public ResponseEntity<CreateFacilityResponse> createFacility(
      @Valid @NotNull @RequestBody CreateFacilityRequest request) {
    var id = facilityService.createFacility(request);
    return new ResponseEntity<>(new CreateFacilityResponse(id), HttpStatus.CREATED);
  }

  /**
   * getFacility.
   */
  @Operation(
      summary = "Get a facility",
      description = "Retrieves a facility by facility ID.",
      parameters = {
          @Parameter(
              name = "id",
              description = "The ID of the facility to retrieve, Cannot be empty",
              required = true)
      },
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Successful operation",
              content = @Content(
                  schema = @Schema(implementation = GetFacilityResponse.class),
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
  @GetMapping(value = "{id}")
  public ResponseEntity<GetFacilityResponse> getFacility(@NotNull @PathVariable UUID id) {
    return new ResponseEntity<>(
        new GetFacilityResponse(facilityService.getFacility(id)), HttpStatus.OK);
  }

  /**
   * updateFacility.
   */
  @Operation(
      summary = "Update a Facility",
      description = "Update a facility.",
      parameters = {
          @Parameter(
              name = "id",
              description = "ID of the facility to update, Cannot be empty",
              required = true)
      },
      responses = {
          @ApiResponse(
              responseCode = "204",
              description = "Successful operation"),
          @ApiResponse(
              responseCode = "404",
              description = "Facility not found",
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
  @PutMapping(value = "{id}")
  public ResponseEntity<Void> updateFacility(
      @NotNull @PathVariable UUID id,
      @Valid @NotNull @RequestBody UpdateFacilityRequest request) {
    facilityService.updateFacility(id, request);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * deleteFacility.
   */
  @Operation(
      summary = "Delete a Facility",
      description = "Delete a facility.",
      parameters = {
          @Parameter(
              name = "id",
              description = "ID of the facility to delete, Cannot be empty",
              required = true)
      },
      responses = {
          @ApiResponse(
              responseCode = "204",
              description = "Successful operation"),
          @ApiResponse(
              responseCode = "404",
              description = "Facility not found",
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
  @DeleteMapping(value = "{id}")
  public ResponseEntity<Void> deleteFacility(@NotNull @PathVariable UUID id) {
    facilityService.deleteFacility(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * activateFacility.
   */
  @Operation(
      summary = "Activate a Facility",
      description = "Activate a facility.",
      parameters = {
          @Parameter(
              name = "id",
              description = "ID of the facility to activate, Cannot be empty",
              required = true)
      },
      responses = {
          @ApiResponse(
              responseCode = "204",
              description = "Successful operation"),
          @ApiResponse(
              responseCode = "404",
              description = "Facility not found",
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
  @PostMapping(value = "{id}/activate")
  public ResponseEntity<Void> activateFacility(@NotNull @PathVariable UUID id) {
    facilityService.activateFacility(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * deactivateFacility.
   */
  @Operation(
      summary = "Deactivate a Facility",
      description = "Deactivate a facility.",
      parameters = {
          @Parameter(
              name = "id",
              description = "ID of the facility to deactivate, Cannot be empty",
              required = true)
      },
      responses = {
          @ApiResponse(
              responseCode = "204",
              description = "Successful operation"),
          @ApiResponse(
              responseCode = "404",
              description = "Facility not found",
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
  @PostMapping(value = "{id}/deactivate")
  public ResponseEntity<Void> deactivateFacility(@NotNull @PathVariable UUID id) {
    facilityService.deactivateFacility(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * closeFacility.
   */
  @Operation(
      summary = "Close a Facility",
      description = "Close a facility.",
      parameters = {
          @Parameter(
              name = "id",
              description = "ID of the facility to close, Cannot be empty",
              required = true)
      },
      responses = {
          @ApiResponse(
              responseCode = "204",
              description = "Successful operation"),
          @ApiResponse(
              responseCode = "404",
              description = "Facility not found",
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
  @PostMapping(value = "{id}/close")
  public ResponseEntity<Void> closeFacility(@NotNull @PathVariable UUID id) {
    facilityService.closeFacility(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * findFacilities.
   */
  @Operation(
      summary = "Get facilities",
      description = "Retrieves a facility by facility ID.",
      parameters = {
          @Parameter(
              name = "id",
              description = "The ID of the facility to retrieve, Cannot be empty",
              required = true)
      },
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Successful operation",
              content = @Content(
                  schema = @Schema(implementation = GetFacilitiesResponse.class),
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
  @GetMapping
  public ResponseEntity<GetFacilitiesResponse> findFacilities(
      @RequestParam(required = false) Facility.Type type,
      @RequestParam(required = false) Set<String> services,
      @RequestParam(defaultValue = "ACTIVE") Facility.Status status,
      @RequestParam(defaultValue = "1") Integer page,
      @RequestParam(defaultValue = "20") Integer size) {
    var facilities = facilityService.findFacilities(type, status, page - 1, size);
    return new ResponseEntity<>(new GetFacilitiesResponse(facilities.getContent(),
        facilities.getTotalPages(), facilities.getTotalElements(), facilities.getNumber() + 1),
        HttpStatus.OK);
  }
}
