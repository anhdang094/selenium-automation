package vn.betting.automation.core.schedule;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import vn.betting.automation.core.service.BettingService;

/**
 * @author anhdx
 */
@Configuration
@EnableScheduling
@PropertySource({"classpath:${appenv}.config.properties"})
public class BettingSchedule {

    private static Logger _logger = LoggerFactory.getLogger(BettingSchedule.class);

    @Autowired
    private Environment env;

    @Autowired
    private BettingService bettingService;

    @Scheduled(cron = "${app.betting.schedule}")
    public void bettingSoccer() {
        try {
            bettingService.bettingSoccer();
        } catch (Exception e) {
            _logger.error(ExceptionUtils.getStackTrace(e));
        }
    }

}
