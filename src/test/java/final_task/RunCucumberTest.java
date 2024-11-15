package final_task;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

    @Suite
    @IncludeEngines("cucumber")
    @SelectPackages("final_task")
    public class RunCucumberTest {
    }

