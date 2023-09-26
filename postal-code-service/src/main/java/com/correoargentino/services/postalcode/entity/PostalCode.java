package com.correoargentino.services.postalcode.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

/**
 * PostalCode
 */
@Data
@RedisHash("postal_codes")
public class PostalCode {
  @Id
  private String code;
  @Indexed
  private String region;
  private String hub;
  private String circuit;
}
