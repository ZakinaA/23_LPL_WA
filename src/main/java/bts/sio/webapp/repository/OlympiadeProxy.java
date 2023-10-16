package bts.sio.webapp.repository;

import bts.sio.webapp.CustomProperties;
import bts.sio.webapp.model.Athlete;
import bts.sio.webapp.model.Olympiade;
import bts.sio.webapp.model.Sport;
import bts.sio.webapp.model.Ville;
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
public class OlympiadeProxy {
    @Autowired
    private CustomProperties props;

    /**
     * Get all athletes
     * @return An iterable of all sport
     */
    public Iterable<Olympiade> getOlympiades() {

        String baseApiUrl = props.getApiUrl();
        String getOlympiadesUrl = baseApiUrl + "/olympiades";
        System.out.println("url=" + getOlympiadesUrl);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Olympiade>> response = restTemplate.exchange(
                getOlympiadesUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Olympiade>>() {}
        );

        log.debug("Get villes call " + response.getStatusCode().toString());

        return response.getBody();
    }

    /**
     * Get an athlete by the id
     * @param id The id of the athlete
     * @return The sport which matches the id
     */
    public Olympiade getOlympiade(int id) {
        String baseApiUrl = props.getApiUrl();
        String getOlympiadeUrl = baseApiUrl + "/olympiade/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Olympiade> response = restTemplate.exchange(
                getOlympiadeUrl,
                HttpMethod.GET,
                null,
                Olympiade.class
        );

        log.debug("Get olympiade call " + response.getStatusCode().toString());

        return response.getBody();
    }

    /**
     * Add a new athlete
     * @param a A new athlete (without an id)
     * @return The sport full filled (with an id)
     */
    public Olympiade createOlympiade(Olympiade a) {

        String baseApiUrl = props.getApiUrl();
        String createOlympiadeUrl = baseApiUrl + "/olympiade";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Olympiade> request = new HttpEntity<Olympiade>(a);
        ResponseEntity<Olympiade> response = restTemplate.exchange(
                createOlympiadeUrl,
                HttpMethod.POST,
                request,
                Olympiade.class);

        log.debug("Create Olympiade call " + response.getStatusCode().toString());

        return response.getBody();
    }

    /**
     * Update an athlete - using the PUT HTTP Method.
     * @param e Existing athlete to update
     */
    public Olympiade updateOlympiade(Olympiade e) {
        String baseApiUrl = props.getApiUrl();
        String updateOlympiadeUrl = baseApiUrl + "/olympiade/" + e.getId();

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Olympiade> request = new HttpEntity<Olympiade>(e);
        ResponseEntity<Olympiade> response = restTemplate.exchange(
                updateOlympiadeUrl,
                HttpMethod.PUT,
                request,
                Olympiade.class);

        log.debug("Update Olympiade call " + response.getStatusCode().toString());

        return response.getBody();
    }

    /*
     * Delete an athlete using exchange method of RestTemplate
     * instead of delete method in order to log the response status code.
     * @param e The athlete to delete
     */
    public void deleteOlympiade(int id) {
        String baseApiUrl = props.getApiUrl();
        String deleteOlympiadeUrl = baseApiUrl + "/olympiade/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.exchange(
                deleteOlympiadeUrl,
                HttpMethod.DELETE,
                null,
                Void.class);

        log.debug("Delete olympiade call " + response.getStatusCode().toString());
    }
}
