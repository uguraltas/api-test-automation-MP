package steps;

import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static steps.ApiSteps.objectId;

public class Hook {
    private static final Logger logger = LogManager.getLogger(Hook.class);
    public static String scenarioName;

    @Before
    public void beforeScenario(Scenario scenario) {
        scenarioName = scenario.getName();
        logger.info("Starting Scenario: " + scenarioName);
    }
}
