package bts.sio.webapp.repository;

import bts.sio.webapp.CustomProperties;
import bts.sio.webapp.model.Athlete;
import bts.sio.webapp.model.Championnat;
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
public class ChampionnatProxy {

    @Autowired
    private CustomProperties props;


    public Iterable<Championnat> getChampionnats() {

        String baseApiUrl = props.getApiUrl();
        String getChampionnatUrl = baseApiUrl + "/championnats";
        System.out.println("url=" + getChampionnatUrl);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Championnat>> response = restTemplate.exchange (
                getChampionnatUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Championnat>>() {}
        );

        log.debug("Get Championnat call " + response.getStatusCode().toString());

        return response.getBody();
    }

    public Championnat getChampionnat(int id) {

        String baseApiUrl = props.getApiUrl();
        String getChampionnatUrl = baseApiUrl + "/championnat/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Championnat> response = restTemplate.exchange(
                getChampionnatUrl,
                HttpMethod.GET,
                null,
                Championnat.class

        );

        log.debug("Get Championnat call " + response.getStatusCode().toString());

        return response.getBody();
    }

    public Championnat createChampionnat(Championnat c) {

        String baseApiUrl = props.getApiUrl();
        String createChampionnatUrl = baseApiUrl + "/championna";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Championnat> request = new HttpEntity<Championnat>(c);
        ResponseEntity<Championnat> response = restTemplate.exchange(
                createChampionnatUrl,
                HttpMethod.POST,
                request,
                Championnat.class
        );

        log.debug("Create Championnat call" + response.getStatusCode().toString());

        return response.getBody();
    }

    public Championnat updateChampionnat(Championnat ch) {

        String baseApiUrl = props.getApiUrl();
        String updateChampionnatUrl = baseApiUrl + "/championnat/" + ch.getId();

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Championnat> request = new HttpEntity<Championnat>(ch);
        ResponseEntity<Championnat> response = restTemplate.exchange(

                updateChampionnatUrl,
                HttpMethod.PUT,
                request,
                Championnat.class

        );

        log.debug("Update Championnat call " + response.getStatusCode().toString());

        return response.getBody();
    }

    public void deleteChampionnat(int id) {

        String baseApiUrl = props.getApiUrl();
        String deleteChampionnatUrl = baseApiUrl + "/championnat/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.exchange(
                deleteChampionnatUrl,
                HttpMethod.DELETE,
                null,
                Void.class);

        log.debug("Delete Championnat call " + response.getStatusCode().toString());
    }


}
