package vn.betting.automation.core.configuration.application;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AccessInfo {

  private String servletPath;
  private Set<String> apiKeys;
  private String env;

  public AccessInfo() {
  }

  public String getServletPath() {
    return servletPath;
  }

  public void setServletPath(String servletPath) {
    this.servletPath = servletPath;
  }

  public Set<String> getApiKeys() {
    return apiKeys;
  }

  public void setApiKeys(String strApiKeys) {
    this.apiKeys = Stream.of(strApiKeys.split(",")).collect(Collectors.toSet());
  }

  public void setApiKeys(Set<String> apiKeys) {
    this.apiKeys = apiKeys;
  }

  public String getEnv() {
    return env;
  }

  public void setEnv(String env) {
    this.env = env;
  }
}
