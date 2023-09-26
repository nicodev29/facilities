package com.correoargentino.services.postalcode.service.impl;

import com.correoargentino.services.postalcode.entity.PostalCode;
import com.correoargentino.services.postalcode.exception.PostalCodeNotFoundException;
import com.correoargentino.services.postalcode.respository.PostalCodeRepository;
import com.correoargentino.services.postalcode.service.PostalCodeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * PostalCodeServiceImpl.
 */
@Service
@RequiredArgsConstructor
public class PostalCodeServiceImpl implements PostalCodeService {
  private final PostalCodeRepository postalCodeRepository;

  @Override
  public PostalCode getPostalCode(String code) {
    return postalCodeRepository.findById(code)
        .orElseThrow(() -> new PostalCodeNotFoundException(code));
  }

  @Override
  public List<PostalCode> getPostalCodes(String region) {
    if (region == null || region.isEmpty() || region.trim().isEmpty()) {
      return postalCodeRepository.findAll();
    } else {
      return postalCodeRepository.findAllByRegion(region);
    }
  }
}
