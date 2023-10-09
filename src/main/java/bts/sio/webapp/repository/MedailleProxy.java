package bts.sio.webapp.repository;
import bts.sio.webapp.CustomProperties;
import bts.sio.webapp.model.Athlete;
import bts.sio.webapp.model.Medaille;
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
public class MedailleProxy {

    @Autowired
    private CustomProperties props;

    /**
     * Get all medailles
     * @return An iterable of all medaille
     */
    public Iterable<Medaille> getMedailles() {

        String baseApiUrl = props.getApiUrl();
        String getMedaillesUrl = baseApiUrl + "/medailles";
        System.out.println("url=" + getMedaillesUrl);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Medaille>> response = restTemplate.exchange(
                getMedaillesUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Medaille>>() {}
        );
        log.debug("Get Medailles call " + response.getStatusCode().toString());

        return response.getBody();
    }

    /**
     * Get an medaille by the id
     * @param id The id of the medaille
     * @return The medaille which matches the id
     */
    public Medaille getMedaille(int id) {
        String baseApiUrl = props.getApiUrl();
        String getMedailleUrl = baseApiUrl + "/medaille/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Medaille> response = restTemplate.exchange(
                getMedailleUrl,
                HttpMethod.GET,
                null,
                Medaille.class
        );

        log.debug("Get Medaille call " + response.getStatusCode().toString());

        return response.getBody();
    }

    /**
     * Add a new medaille
     * @param a A new medaille (without an id)
     * @return The medaille full filled (with an id)
     */
    public Medaille createMedaille(Medaille a) {

        String baseApiUrl = props.getApiUrl();
        String createMedailleUrl = baseApiUrl + "/medaille";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Medaille> request = new HttpEntity<Medaille>(a);
        ResponseEntity<Medaille> response = restTemplate.exchange(
                createMedailleUrl,
                HttpMethod.POST,
                request,
                Medaille.class);

        log.debug("Create Medaille call " + response.getStatusCode().toString());

        return response.getBody();
    }

    /**
     * Update an medaille - using the PUT HTTP Method.
     * @param e Existing medaille to update
     */
    public Medaille updateMedaille(Medaille e) {
        String baseApiUrl = props.getApiUrl();
        String updateMedailleUrl = baseApiUrl + "/medaille/" + e.getId();

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Medaille> request = new HttpEntity<Medaille>(e);
        ResponseEntity<Medaille> response = restTemplate.exchange(
                updateMedailleUrl,
                HttpMethod.PUT,
                request,
                Medaille.class);

        log.debug("Update Medaille call " + response.getStatusCode().toString());

        return response.getBody();
    }

    /*
     * Delete an medaille using exchange method of RestTemplate
     * instead of delete method in order to log the response status code.
     * @param e The medaille to delete
     */
    public void deleteMedaille(int id) {
        String baseApiUrl = props.getApiUrl();
        String deleteMedailleUrl = baseApiUrl + "/medaille/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.exchange(
                deleteMedailleUrl,
                HttpMethod.DELETE,
                null,
                Void.class);

        log.debug("Delete Medaille call " + response.getStatusCode().toString());
    }
}

