package vn.betting.automation.core.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vn.betting.automation.core.Main;
import vn.betting.automation.core.service.BettingService;

@RestController
public class ExternalApiController extends AbstractController {

    @Autowired
    private BettingService BettingService;

    @Autowired
    private Environment env;

    @RequestMapping(value = "/restart", method = RequestMethod.POST)
    public void restart() {
        Main.restart();
    }
}
