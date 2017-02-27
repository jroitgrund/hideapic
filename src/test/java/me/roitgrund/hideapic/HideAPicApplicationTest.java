package me.roitgrund.hideapic;


import com.palantir.docker.compose.DockerComposeRule;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.ClassRule;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HideAPicApplicationTest {

    @ClassRule
    public static DockerComposeRule dockerComposeRule = new DockerComposeRule.Builder()
            .file("docker-compose.yml")
            .build();

    @Test
    public void test() throws Exception {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet("http://localhost:8081/healthcheck");
        assertThat(client.execute(request).getStatusLine().getStatusCode()).isEqualTo(200);
    }
}