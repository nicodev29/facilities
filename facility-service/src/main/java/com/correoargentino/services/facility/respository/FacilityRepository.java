package com.correoargentino.services.facility.respository;

import com.correoargentino.services.facility.entity.Facility;
import java.util.UUID;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.ListQueryByExampleExecutor;
import org.springframework.stereotype.Repository;

/**
 * FacilityRepository.
 */
@Repository
public interface FacilityRepository
    extends ListCrudRepository<Facility, UUID>, ListQueryByExampleExecutor<Facility> {
}
