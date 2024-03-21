package com.hexaware.CozyHeavenStay.config;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomDoubleSerializer extends JsonSerializer<Double> {
    @Override
    public void serialize(Double value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value == null) {
            gen.writeNull();
        } else {
            gen.writeNumber(value);
        }
    }
}
