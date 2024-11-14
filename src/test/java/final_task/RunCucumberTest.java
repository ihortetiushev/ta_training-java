package final_task;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

    @Suite
    @IncludeEngines("cucumber")
    @SelectPackages("final_task")
   // @ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "UseCase1BDDRest.feature")
    public class RunCucumberTest {
    }

