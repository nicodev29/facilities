package com.correoargentino.services.facility.request;

import com.correoargentino.services.facility.entity.Address;
import com.correoargentino.services.facility.entity.Facility;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * UpdateFacilityRequest.
 */
public record UpdateFacilityRequest(
    UUID hubId,
    @NotBlank @Size(max = 10) String code,
    @NotNull Facility.Type type,
    @NotBlank @Size(max = 10) String number,
    @NotBlank @Size(max = 80) String name,
    @Size(max = 255) String manager,
    @Email @Size(max = 255) String emailAddress,
    @Pattern(regexp = "^\\+[1-9]{1}[0-9]{3,14}$") String phoneNumber,
    Address address,
    Map<String, Object> attributes,
    Set<String> services,
    @NotNull Facility.Status status
) {
}
