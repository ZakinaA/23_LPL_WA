package bts.sio.webapp.service;

import bts.sio.webapp.model.Athlete;
import bts.sio.webapp.model.Championnat;
import bts.sio.webapp.repository.AthleteProxy;
import bts.sio.webapp.repository.ChampionnatProxy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service

public class ChampionnatService {

    @Autowired
    private ChampionnatProxy ChampionnatProxy;

    public Championnat getChampionnat(final int id) {
        return ChampionnatProxy.getChampionnat(id);
    }

    public Iterable<Championnat> getChampionnats() {
        return ChampionnatProxy.getChampionnats();
    }

    public void deleteChampionnat(final int id) {
        ChampionnatProxy.deleteChampionnat(id);
    }

    public Championnat saveChampionnat(Championnat championnat) {
        Championnat savedChampionnat;

        // Functional rule : Last name must be capitalized.
        championnat.setLibelle(championnat.getLibelle().toUpperCase());

        if(championnat.getId() == null) {
            // If id is null, then it is a new employee.
            savedChampionnat = ChampionnatProxy.createChampionnat(championnat);
        } else {
            savedChampionnat = ChampionnatProxy.updateChampionnat(championnat);
        }

        return savedChampionnat;
    }
}
