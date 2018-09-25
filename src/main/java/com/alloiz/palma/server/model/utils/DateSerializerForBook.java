package com.alloiz.palma.server.model.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

public class DateSerializerForBook extends JsonSerializer<Timestamp> {

    @Override
    public void serialize(Timestamp timestamp, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeString(timestamp.toLocalDateTime() != null ?
                timestamp.toLocalDateTime().format(DateTimeFormatter.ISO_DATE_TIME) : "null");
    }
}
