package bts.sio.webapp.service;

import bts.sio.webapp.model.Palmares;
import bts.sio.webapp.repository.PalmaresProxy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class PalmaresService {
    @Autowired
    private PalmaresProxy palmaresProxy;

    public Palmares getPalmares(final int id){return palmaresProxy.getPalmares(id);}

    public Iterable<Palmares> getPalmaress(){return palmaresProxy.getPalmaress();}

    public void deletePalmares(final int id) {palmaresProxy.deletePalmares(id);}

    public Palmares savePalmares(Palmares palmares){
        Palmares savedPalmares;

        palmares.setAnnee(palmares.getAnnee());

        if(palmares.getId() == null) {
            savedPalmares = palmaresProxy.createPalmares(palmares);
        }else {
            savedPalmares = palmaresProxy.updatePalmares(palmares);
        }
        return savedPalmares;
    }
}
