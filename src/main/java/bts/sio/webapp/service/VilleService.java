package bts.sio.webapp.service;

import bts.sio.webapp.model.Sport;
import bts.sio.webapp.model.Ville;
import bts.sio.webapp.repository.SportProxy;
import bts.sio.webapp.repository.VilleProxy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class VilleService {
    @Autowired
    private VilleProxy villeProxy;

    public Ville getVille(final int id){return villeProxy.getVille(id);}

    public Iterable<Ville> getVilles(){return villeProxy.getVilles();}

    public void deleteVille(final int id ) { villeProxy.deleteVille(id);}

    public Ville saveVille(Ville ville){
        Ville savedVille;
        ville.setNom(ville.getNom());
        ville.setPays(ville.getPays());


        if(ville.getId() == null){
            savedVille = villeProxy.createVille(ville);

        }else {
            savedVille = villeProxy.updateVille(ville);
        }
        return savedVille;
    }
}
