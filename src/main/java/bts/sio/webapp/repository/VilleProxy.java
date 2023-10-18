package bts.sio.webapp.repository;

import bts.sio.webapp.CustomProperties;
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
public class VilleProxy {
    @Autowired
    private CustomProperties props;


    public Iterable<Ville> getVilles() {

        String baseApiUrl = props.getApiUrl();
        String getVillesUrl = baseApiUrl + "/villes";
        System.out.println("url=" + getVillesUrl);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Ville>> response = restTemplate.exchange(
                getVillesUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Ville>>() {}
        );

        log.debug("Get villes call " + response.getStatusCode().toString());

        return response.getBody();
    }


    public Ville getVille(int id) {
        String baseApiUrl = props.getApiUrl();
        String getVilleUrl = baseApiUrl + "/ville/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Ville> response = restTemplate.exchange(
                getVilleUrl,
                HttpMethod.GET,
                null,
                Ville.class
        );

        log.debug("Get ville call " + response.getStatusCode().toString());

        return response.getBody();
    }


    public Ville createVille(Ville a) {

        String baseApiUrl = props.getApiUrl();
        String createVilleUrl = baseApiUrl + "/ville";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Ville> request = new HttpEntity<Ville>(a);
        ResponseEntity<Ville> response = restTemplate.exchange(
                createVilleUrl,
                HttpMethod.POST,
                request,
                Ville.class);

        log.debug("Create Ville call " + response.getStatusCode().toString());

        return response.getBody();
    }


    public Ville updateVille(Ville e) {
        String baseApiUrl = props.getApiUrl();
        String updateVilleUrl = baseApiUrl + "/ville/" + e.getId();

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Ville> request = new HttpEntity<Ville>(e);
        ResponseEntity<Ville> response = restTemplate.exchange(
                updateVilleUrl,
                HttpMethod.PUT,
                request,
                Ville.class);

        log.debug("Update Ville call " + response.getStatusCode().toString());

        return response.getBody();
    }


    public void deleteVille(int id) {
        String baseApiUrl = props.getApiUrl();
        String deleteVilleUrl = baseApiUrl + "/ville/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.exchange(
                deleteVilleUrl,
                HttpMethod.DELETE,
                null,
                Void.class);

        log.debug("Delete ville call " + response.getStatusCode().toString());
    }
}
