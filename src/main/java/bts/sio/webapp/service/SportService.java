package bts.sio.webapp.service;

import bts.sio.webapp.model.Sport;
import bts.sio.webapp.repository.SportProxy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class SportService {
    @Autowired
    private SportProxy sportProxy;

    public Sport getSport(final int id){return sportProxy.getSport(id);}

    public Iterable<Sport> getSports(){return sportProxy.getSports();}

    public void deleteSport(final int id ) { sportProxy.deleteSport(id);}

    public Sport saveSport(Sport sport){
        Sport savedSport;
        sport.setNom(sport.getNom());
        sport.setDescriptif(sport.getDescriptif());
        sport.setOlympiade(sport.getOlympiade());
        sport.setNomImage(sport.getNomImage());


        if(sport.getId() == null){
            savedSport = sportProxy.createSport(sport);

        }else {
            savedSport = sportProxy.updateSport(sport);
        }
        return savedSport;
    }
}
