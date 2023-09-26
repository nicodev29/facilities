package com.correoargentino.services.postalcode.controller;

import com.correoargentino.services.postalcode.response.ErrorResponse;
import com.correoargentino.services.postalcode.response.GetPostalCodeResponse;
import com.correoargentino.services.postalcode.response.GetPostalCodesResponse;
import com.correoargentino.services.postalcode.service.PostalCodeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
 * PostalCodeController.
 */
@Slf4j
@Validated
@RestController
@RequestMapping(value = "v1/postal-codes", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Postal Codes")
@RequiredArgsConstructor
public class PostalCodeController {
  private final PostalCodeService postalCodeService;

  /**
   * getPostalCode.
   */
  @Operation(
      summary = "Get Postal Code",
      description = "Retrieves a postal code by code.",
      parameters = {
        @Parameter(name = "code",
            description = "The code of the postal code to retrieve, Cannot be empty",
            required = true)},
      responses = {
        @ApiResponse(responseCode = "200", description = "Successful operation",
            content = @Content(schema = @Schema(implementation = GetPostalCodeResponse.class),
                mediaType = MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(responseCode = "404", description = "Postal Code not found",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class),
                mediaType = MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(responseCode = "500", description = "Service not available",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class),
                mediaType = MediaType.APPLICATION_JSON_VALUE))})
  @GetMapping(value = "{code}")
  public ResponseEntity<GetPostalCodeResponse> getPostalCode(@NotNull @PathVariable String code) {
    return new ResponseEntity<>(new GetPostalCodeResponse(
        postalCodeService.getPostalCode(code)), HttpStatus.OK);
  }

  /**
   * getPostalCodes.
   */
  @Operation(summary = "Search or List Postal Codes",
      description = "Retrieves a postal code by code.",
      parameters = {
        @Parameter(name = "region",
            description = "The region (state or province) of the postal code to retrieve",
            required = false)},
      responses = {
        @ApiResponse(responseCode = "200",
            description = "Successful operation",
            content = @Content(schema = @Schema(implementation = GetPostalCodesResponse.class),
                mediaType = MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(responseCode = "500",
            description = "Service not available",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class),
                mediaType = MediaType.APPLICATION_JSON_VALUE))})
  @GetMapping
  public ResponseEntity<GetPostalCodesResponse> getPostalCodes(
      @RequestParam(required = false) String region) {
    return new ResponseEntity<>(
        new GetPostalCodesResponse(postalCodeService.getPostalCodes(region)), HttpStatus.OK);
  }
}
