package bts.sio.webapp.repository;

import bts.sio.webapp.CustomProperties;
import bts.sio.webapp.model.Athlete;
import bts.sio.webapp.model.Palmares;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class PalmaresProxy {

    @Autowired
    private CustomProperties props;

    public Iterable<Palmares> getPalmaress() {

        String baseApiUrl = props.getApiUrl();

        String getPalmaressUrl = baseApiUrl + "/palmaress";
        System.out.println("url=" + getPalmaressUrl);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Palmares>> response = restTemplate.exchange(
                getPalmaressUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Palmares>>() {}
        );

        log.debug("Get palmaress call " + response.getStatusCode().toString());

        return response.getBody();
    }

    public Palmares getPalmares(int id) {
        String baseApiUrl = props.getApiUrl();
        String getPalmaresUrl = baseApiUrl + "/palmares/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Palmares> response = restTemplate.exchange(
                getPalmaresUrl,
                HttpMethod.GET,
                null,
                Palmares.class
        );

        log.debug("Get Palmares call " + response.getStatusCode().toString());

        return response.getBody();
    }

    public Palmares createPalmares(Palmares p) {

        String baseApiUrl = props.getApiUrl();
        String createPalmaresUrl = baseApiUrl + "/palmares";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Palmares> request = new HttpEntity<Palmares>(p);
        ResponseEntity<Palmares> response = restTemplate.exchange(
                createPalmaresUrl,
                HttpMethod.POST,
                request,
                Palmares.class);

        log.debug("Create Palmares call " + response.getStatusCode().toString());

        return response.getBody();
    }

    public Palmares updatePalmares(Palmares pa) {
        String baseApiUrl = props.getApiUrl();
        String updatePalmaresUrl = baseApiUrl + "/palmares/" + pa.getId();

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Palmares> request = new HttpEntity<Palmares>(pa);
        ResponseEntity<Palmares> response = restTemplate.exchange(
                updatePalmaresUrl,
                HttpMethod.PUT,
                request,
                Palmares.class);

        log.debug("Update palmares call " + response.getStatusCode().toString());

        return response.getBody();
    }

    public void deletePalmares(int id) {
        String baseApiUrl = props.getApiUrl();
        String deletePalmaresUrl = baseApiUrl + "/palmares/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.exchange(
                deletePalmaresUrl,
                HttpMethod.DELETE,
                null,
                Void.class);

        log.debug("Delete Palmares call " + response.getStatusCode().toString());
    }
}
