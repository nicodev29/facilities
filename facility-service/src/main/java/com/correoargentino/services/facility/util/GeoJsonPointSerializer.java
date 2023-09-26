package com.correoargentino.services.facility.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

/**
 * GeoJsonPointSerializer.
 */
public class GeoJsonPointSerializer extends JsonSerializer<GeoJsonPoint> {

  @Override
  public void serialize(GeoJsonPoint geoJsonPoint, JsonGenerator jsonGenerator,
                        SerializerProvider serializerProvider) throws IOException {
    jsonGenerator.writeStartObject();
    jsonGenerator.writeNumberField("latitude", geoJsonPoint.getX());
    jsonGenerator.writeNumberField("longitude", geoJsonPoint.getY());
    jsonGenerator.writeEndObject();
  }
}
