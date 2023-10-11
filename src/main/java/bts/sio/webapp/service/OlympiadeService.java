package bts.sio.webapp.service;

import bts.sio.webapp.model.Olympiade;
import bts.sio.webapp.model.Sport;
import bts.sio.webapp.model.Ville;
import bts.sio.webapp.repository.OlympiadeProxy;
import bts.sio.webapp.repository.SportProxy;
import bts.sio.webapp.repository.VilleProxy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class OlympiadeService {
    @Autowired
    private OlympiadeProxy olympiadeProxy;

    public Olympiade getOlympiade(final int id){return olympiadeProxy.getOlympiade(id);}

    public Iterable<Olympiade> getOlympiades(){return olympiadeProxy.getOlympiades();}

    public void deleteOlympiade(final int id ) { olympiadeProxy.deleteOlympiade(id);}

    public Olympiade saveOlympiade(Olympiade Olympiade){
        Olympiade savedOlympiade;
        Olympiade.setAnnee(Olympiade.getAnnee());
        Olympiade.setVille(Olympiade.getVille());
        Olympiade.setNumero(Olympiade.getNumero());


        if(Olympiade.getId() == null){
            savedOlympiade = olympiadeProxy.createOlympiade(Olympiade);

        }else {
            savedOlympiade = olympiadeProxy.updateOlympiade(Olympiade);
        }
        return savedOlympiade;
    }
}
