package com.correoargentino.services.facility.service.impl;

import com.correoargentino.services.facility.entity.Facility;
import com.correoargentino.services.facility.exception.FacilityAlreadyExistsException;
import com.correoargentino.services.facility.exception.FacilityNotFoundException;
import com.correoargentino.services.facility.mapper.FacilityMapper;
import com.correoargentino.services.facility.request.CreateFacilityRequest;
import com.correoargentino.services.facility.request.UpdateFacilityRequest;
import com.correoargentino.services.facility.respository.FacilityRepository;
import com.correoargentino.services.facility.service.FacilityService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * FacilityServiceImpl.
 */
@Service
@RequiredArgsConstructor
public class FacilityServiceImpl implements FacilityService {
  private final FacilityRepository facilityRepository;
  private final FacilityMapper facilityMapper;

  @Override
  public UUID createFacility(CreateFacilityRequest request) {
    var facility = facilityMapper.requestToFacility(request);

    try {
      facilityRepository.save(facility);
      return facility.getId();
    } catch (DuplicateKeyException e) {
      throw new FacilityAlreadyExistsException(request.code());
    }
  }

  @Override
  public Facility getFacility(UUID id) {
    return facilityRepository.findById(id).orElseThrow(() -> new FacilityNotFoundException(id));
  }

  @Override
  public void updateFacility(UUID id, UpdateFacilityRequest request) {
    var facility = facilityRepository.findById(id)
        .orElseThrow(() -> new FacilityNotFoundException(id));

    facilityMapper.updateFacilityFromRequest(facility, request);

    try {
      facilityRepository.save(facility);
    } catch (DuplicateKeyException e) {
      throw new FacilityAlreadyExistsException(request.code());
    }
  }

  @Override
  public void deleteFacility(UUID id) {
    var facility = facilityRepository.findById(id)
        .orElseThrow(() -> new FacilityNotFoundException(id));
    facilityRepository.delete(facility);
  }

  @Override
  public void activateFacility(UUID id) {
    var facility = facilityRepository.findById(id)
        .orElseThrow(() -> new FacilityNotFoundException(id));
    facility.setStatus(Facility.Status.ACTIVE);
    facilityRepository.save(facility);
  }

  @Override
  public void deactivateFacility(UUID id) {
    var facility = facilityRepository.findById(id)
        .orElseThrow(() -> new FacilityNotFoundException(id));
    facility.setStatus(Facility.Status.INACTIVE);
    facilityRepository.save(facility);
  }

  @Override
  public void closeFacility(UUID id) {
    var facility = facilityRepository.findById(id)
        .orElseThrow(() -> new FacilityNotFoundException(id));
    facility.setStatus(Facility.Status.CLOSED);
    facilityRepository.save(facility);
  }

  @Override
  public Page<Facility> findFacilities(
      Facility.Type type, Facility.Status status, Integer page, Integer size) {
    var facility = new Facility();
    facility.setType(type);
    facility.setStatus(status);

    ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
    Example<Facility> example = Example.of(facility, matcher);

    var pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc("id")));

    return facilityRepository.findBy(example, q -> q.page(pageable));
  }
}
