package core.test.cucumber.stepdefs;

import core.test.SapoAPI;
import core.test.config.ApplicationProperties;
import core.test.util.TestUtil;

import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.http.client.methods.HttpHead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import org.springframework.boot.test.context.SpringBootTest;

@WebAppConfiguration
@SpringBootTest
@ContextConfiguration(classes = SapoAPI.class)
public abstract class StepDefs {
    @Autowired
    protected ObjectMapper jsonMapper;
    @Autowired
    protected TestRestTemplate restTemplate;
    @Autowired
    private ApplicationProperties applicationProperties;

    protected final HttpHeaders headers = TestUtil.createJsonHeaders();

    public String getBaseUrl() {
        return applicationProperties.getBaseUrl();
    }

    public String getToken() {
        return applicationProperties.getToken();
    }

    public String getUrl(String uri) {
        return getBaseUrl() + uri;
    }
}
