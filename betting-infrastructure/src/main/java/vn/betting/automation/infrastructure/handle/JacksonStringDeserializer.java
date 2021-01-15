package vn.betting.automation.infrastructure.handle;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;


public class JacksonStringDeserializer extends JsonDeserializer<String> {
    @Override
    public String deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        if (jp.getValueAsString().trim().length() == 0) {
            return null;
        }
        return jp.getValueAsString();
    }
}
