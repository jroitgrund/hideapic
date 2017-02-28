package me.roitgrund.hideapic;


import com.palantir.docker.compose.DockerComposeRule;
import com.palantir.docker.compose.connection.waiting.HealthChecks;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

import static com.palantir.docker.compose.logging.LogDirectory.circleAwareLogDirectory;
import static org.assertj.core.api.Assertions.assertThat;

public class HideAPicApplicationTest {

    @ClassRule
    public static DockerComposeRule dockerComposeRule = new DockerComposeRule.Builder()
            .file("docker-compose.yml")
            .saveLogsTo(circleAwareLogDirectory(HideAPicApplicationTest.class))
            .waitingForService("hideapic", HealthChecks.toRespond2xxOverHttp(
                    8081, port -> port.inFormat("http://$HOST:$EXTERNAL_PORT/healthcheck")))
            .build();

    @Test
    public void test() throws Exception {
        RemoteWebDriver driver = new RemoteWebDriver(
                new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.chrome());
        driver.get("http://hideapic:8080/foo");
        assertThat(driver.findElementByTagName("img").getSize()).isEqualTo(new Dimension(272, 92));
    }
}