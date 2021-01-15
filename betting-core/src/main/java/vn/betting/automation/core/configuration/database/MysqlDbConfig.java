package vn.betting.automation.core.configuration.database;

import java.util.HashMap;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@PropertySource({"classpath:database.properties"})
@EnableJpaRepositories(basePackages = {"vn.betting.automation.core.repository"},
    entityManagerFactoryRef = "entityManager",
    transactionManagerRef = "transactionManager")
public class MysqlDbConfig {

  @Autowired
  private Environment env;

  @Primary
  @Bean
  public LocalContainerEntityManagerFactoryBean entityManager() {
    LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(dataSource());
    em.setPackagesToScan("vn.betting.automation.core.model");

    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    em.setJpaVendorAdapter(vendorAdapter);
    HashMap<String, Object> properties = new HashMap<>();
    properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
    properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
    properties.put("hibernate.naming-strategy", "org.hibernate.cfg.ImprovedNamingStrategy");

    em.setJpaPropertyMap(properties);

    return em;
  }

  @Primary
  @Bean
  public DataSource dataSource() {

    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(env.getProperty("betting.driverClassName"));
    dataSource.setUrl(env.getProperty("betting.url"));
    dataSource.setUsername(env.getProperty("betting.username"));
    dataSource.setPassword(env.getProperty("betting.password"));

    return dataSource;
  }

  @Primary
  @Bean
  public PlatformTransactionManager transactionManager() {

    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(entityManager().getObject());
    return transactionManager;
  }
}
