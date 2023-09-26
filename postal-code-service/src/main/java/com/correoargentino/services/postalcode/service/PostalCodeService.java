package com.correoargentino.services.postalcode.service;

import com.correoargentino.services.postalcode.entity.PostalCode;
import java.util.List;
import org.springframework.lang.Nullable;

public interface PostalCodeService {
  PostalCode getPostalCode(String code);
  List<PostalCode> getPostalCodes(@Nullable String region);
}
