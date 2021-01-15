package vn.betting.automation.core;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;

/**
 * @author anhdx
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class})
@PropertySource(value = {"classpath:${appenv}.config.properties"})
public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    private static ConfigurableApplicationContext ctx;

    public static ApplicationContext getCtx() {
        return ctx;
    }

    public static void main(String[] args) {
        try {
            ctx = SpringApplication.run(Main.class, args);
        } catch (Exception e) {
            logger.error("Start fail", ExceptionUtils.getStackTrace(e));
        }
    }

    public static void restart() {
        ApplicationArguments args = ctx.getBean(ApplicationArguments.class);

        Thread thread = new Thread(() -> {
            ctx.close();
            ctx = SpringApplication.run(Main.class, args.getSourceArgs());
        });

        thread.setDaemon(false);
        thread.start();
    }
}
