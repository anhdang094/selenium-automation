package vn.betting.automation.infrastructure.configuration;

public class BankMantenanceConfig extends ApiConfig {

  public BankMantenanceConfig(String url, String hashKey, int connectionTimeout, int socketTimeout,
                              boolean useProxy, ProxyConfig proxyConfig, int clientId) {
    super(url, hashKey, connectionTimeout, socketTimeout, useProxy, proxyConfig, clientId);
  }
}