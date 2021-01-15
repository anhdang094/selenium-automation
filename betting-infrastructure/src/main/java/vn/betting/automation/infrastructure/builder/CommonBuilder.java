package vn.betting.automation.infrastructure.builder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import vn.betting.automation.infrastructure.utils.GsonUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommonBuilder {

  public static List<NameValuePair> buildNameValuePair(Map<String, String> data) {
    List<NameValuePair> results = new ArrayList<>();
    for (Map.Entry<String, String> entry : data.entrySet()) {
      results.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
    }
    return results;
  }

  public static List<NameValuePair> buildNameValuePair(Object data) throws IOException {
    String json = GsonUtils.toJsonString(data);
    ObjectMapper mapper = new ObjectMapper();
    // convert JSON string to Map
    return buildNameValuePair(mapper.readValue(json, new TypeReference<Map<String, String>>() {
    }));
  }

  public static Map<String, String> buildMapValue(Object data) throws IOException {
    String json = GsonUtils.toJsonString(data);
    ObjectMapper mapper = new ObjectMapper();
    // convert JSON string to Map
    return mapper.readValue(json, new TypeReference<Map<String, String>>() {
    });
  }

}
