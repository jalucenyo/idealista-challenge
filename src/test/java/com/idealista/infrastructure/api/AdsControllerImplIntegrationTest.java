package com.idealista.infrastructure.api;

import com.idealista.Main;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AdsControllerImplIntegrationTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();

    @Test
    public void qualityListing() throws JSONException, IOException {

        calculateScore();

        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:" + port + "/ads/quality",
                HttpMethod.GET, entity, String.class);

        String expected = IOUtils.toString(this.getClass()
                .getResourceAsStream("/qualityListingExpected.json"),"UTF-8");

        assertThatJson(response.getBody()).whenIgnoringPaths("[*].irrelevantSince").isEqualTo(expected);

    }

    @Test
    public void publicListing() throws JSONException, IOException {

        calculateScore();

        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:" + port + "/ads/public",
                HttpMethod.GET, entity, String.class);

        String expected = IOUtils.toString(this.getClass()
                .getResourceAsStream("/publicListingExpected.json"),"UTF-8");

        assertThatJson(response.getBody()).isEqualTo(expected);

    }

    private void calculateScore(){
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        restTemplate.exchange("http://localhost:" + port + "/ads/calculate_score",
                HttpMethod.POST, entity, String.class);
    }

}
