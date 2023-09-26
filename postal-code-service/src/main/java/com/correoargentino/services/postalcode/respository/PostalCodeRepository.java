package com.correoargentino.services.postalcode.respository;

import com.correoargentino.services.postalcode.entity.PostalCode;
import java.util.List;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostalCodeRepository extends ListCrudRepository<PostalCode, String> {
  List<PostalCode> findAllByRegion(String region);
}
