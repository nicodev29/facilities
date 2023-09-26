package com.correoargentino.services.facility.service.impl;

import com.correoargentino.services.facility.entity.Facility;
import com.correoargentino.services.facility.service.ServicePointService;
import com.mongodb.lang.Nullable;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.geo.Distance;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

/**
 * ServicePointServiceImpl.
 */
@Service
@RequiredArgsConstructor
public class ServicePointServiceImpl implements ServicePointService {
  private final MongoTemplate mongoTemplate;

  @Override
  public List<Facility> findServicePoints(List<String> services, String exclusion) {
    var query = new Query();

    if (services != null) {
      query.addCriteria(Criteria.where("services").all(services));
    }

    if (exclusion != null) {
      query.addCriteria(Criteria.where("exclusions").ne(exclusion));
    }

    query.addCriteria(Criteria.where("type").in(List.of("BRANCH", "AGENCY", "LOCKER")));
    query.addCriteria(Criteria.where("status").is(Facility.Status.ACTIVE));

    return mongoTemplate.find(query, Facility.class);
  }

  @Override
  public List<Facility> findServicePointsByGeolocation(
      Double latitude, Double longitude, Distance distance,
      @Nullable List<String> services, @Nullable String exclusion) {
    var query = Query.query(Criteria.where("address.location")
        .near(new GeoJsonPoint(latitude, longitude)).maxDistance(distance.getValue()));

    if (services != null) {
      query.addCriteria(Criteria.where("services").all(services));
    }

    if (exclusion != null) {
      query.addCriteria(Criteria.where("exclusions").ne(exclusion));
    }

    query.addCriteria(Criteria.where("type").in(List.of("BRANCH", "AGENCY", "LOCKER")));
    query.addCriteria(Criteria.where("status").is(Facility.Status.ACTIVE));

    return mongoTemplate.find(query, Facility.class);
  }

  @Override
  public List<Facility> findServicePointsByRegion(
      String region, @Nullable List<String> services, @Nullable String exclusion) {
    var query = Query.query(Criteria.where("address.region").is(region));

    if (services != null) {
      query.addCriteria(Criteria.where("services").all(services));
    }

    if (exclusion != null) {
      query.addCriteria(Criteria.where("exclusions").ne(exclusion));
    }

    query.addCriteria(Criteria.where("type").in(List.of("BRANCH", "AGENCY", "LOCKER")));
    query.addCriteria(Criteria.where("status").is(Facility.Status.ACTIVE));

    return mongoTemplate.find(query, Facility.class);
  }
}
