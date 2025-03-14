package com.ecommerce.order.about;

import com.ecommerce.order.common.logging.AutoNamingLoggerFactory;
import org.slf4j.Logger;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.Arrays;

@RestController
@RequestMapping(value = "/about")
public class AboutController {
    //now
    private ZonedDateTime deployTime = ZonedDateTime.now();

    private Environment environment;

    public AboutController(Environment environment) {
        this.environment = environment;
    }

    private static final Logger logger = AutoNamingLoggerFactory.getLogger();

    @GetMapping
    public AboutRepresentation about() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        logger.info("about...");
        String buildNumber = environment.getProperty("buildNumber");
        String buildTime = environment.getProperty("buildTime");
        String gitRevision = environment.getProperty("gitRevision");
        String gitBranch = environment.getProperty("gitBranch");

        String activeProfiles = Arrays.toString(environment.getActiveProfiles());
        String deployTime = this.deployTime.toString();
        return new AboutRepresentation(buildNumber, buildTime, deployTime, gitRevision, gitBranch, activeProfiles);
    }

}
