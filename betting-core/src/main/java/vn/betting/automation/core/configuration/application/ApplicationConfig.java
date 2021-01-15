package vn.betting.automation.core.configuration.application;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import vn.betting.automation.infrastructure.configuration.ApiConfig;
import vn.betting.automation.infrastructure.configuration.BankMantenanceConfig;
import vn.betting.automation.infrastructure.configuration.ProxyConfig;

@Configuration
@PropertySource(value = {"classpath:${appenv}.config.properties"})
public class ApplicationConfig {

    @Autowired
    private Environment env;

    @Bean
    AccessInfo getAccessInfo() {
        AccessInfo accessInfo = new AccessInfo();
        accessInfo.setServletPath(env.getProperty("server.servlet.path"));
        accessInfo.setApiKeys(env.getProperty("access.info.api-keys"));
        accessInfo.setEnv(env.getProperty("app.enviroment"));
        return accessInfo;
    }

    @Bean
    ApplicationInfo getApplicationInfo() {
        ApplicationInfo applicationInfo = new ApplicationInfo();
        applicationInfo.setAddress(env.getProperty("app.host"));
        return applicationInfo;
    }

    ApiConfig getApiConfig(String name) {
        ProxyConfig proxyConfig = new ProxyConfig();
        proxyConfig.setHost(env.getProperty("proxy.host"));
        proxyConfig.setPort(NumberUtils.toInt(env.getProperty("proxy.port")));

        String url = env.getProperty("api." + name + ".url");
        String hashKey = env.getProperty("api." + name + ".hashkey");
        int connectionTimeout = NumberUtils.toInt(env.getProperty("api." + name + ".sotimeout"));
        int socketTimeout = NumberUtils.toInt(env.getProperty("api." + name + ".timeout"));
        boolean useProxy = BooleanUtils.toBoolean(env.getProperty("api." + name + ".useproxy"));
        int clientId = NumberUtils.toInt(env.getProperty("api." + name + ".clientid"));

        ApiConfig apiConfig = null;
        switch (name) {
            case "cps-bankmantenance":
                apiConfig = new BankMantenanceConfig(url, hashKey, connectionTimeout, socketTimeout, useProxy, proxyConfig, clientId);
                break;
            default:
                break;
        }
        return apiConfig;
    }
}
