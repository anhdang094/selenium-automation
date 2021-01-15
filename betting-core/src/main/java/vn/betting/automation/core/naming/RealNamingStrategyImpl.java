package vn.betting.automation.core.naming;

import java.io.Serializable;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;

public class RealNamingStrategyImpl extends SpringPhysicalNamingStrategy implements Serializable {

  @Override
  public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
    return new Identifier(name.getText(), name.isQuoted());
  }

  @Override
  public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) {
    return new Identifier(name.getText(), name.isQuoted());
  }
}

