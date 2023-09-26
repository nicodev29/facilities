package com.correoargentino.services.facility.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Facility.
 */
@Data
@Document("facilities")
public class Facility {
  @Id
  private UUID id;
  @Indexed
  private UUID hubId;
  @NotBlank
  @Size(min = 3, max = 3)
  @Indexed(unique = true)
  private String code;
  @Indexed
  @NotNull
  private Type type;
  private String number;
  @NotNull
  @Size(max = 80)
  private String name;
  @Size(max = 80)
  private String manager;
  private String emailAddress;
  private String phoneNumber;
  private Address address;
  private Map<String, Object> attributes;
  @Indexed
  private Set<String> services;
  @Indexed
  private Set<String> exclusions;
  @Indexed
  @NotNull
  private Status status;
  @CreatedDate
  private LocalDateTime createdAt;
  @LastModifiedDate
  private LocalDateTime updatedAt;

  /**
   * Facility Type.
   */
  public enum Type {
    BRANCH, AGENCY, LOCKER, HUB, WAREHOUSE
  }

  /**
   * Facility Status.
   */
  public enum Status {
    ACTIVE, INACTIVE, CLOSED
  }
}
