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
        System.out.println("ATHLETE prenom= " + palmares.getAthlete() + "NOM==" + palmares.getVille());
        palmares.setAthlete(palmares.getAthlete());
        palmares.setVille(palmares.getVille());
        palmares.setMedaille(palmares.getMedaille());
        palmares.setChampionnat(palmares.getChampionnat());
        palmares.setAnnee(palmares.getAnnee());

        if(palmares.getId() == null) {
            System.out.println("azeraratest");
            savedPalmares = palmaresProxy.createPalmares(palmares);
        }else {
            System.out.println("azeraratest2");
            savedPalmares = palmaresProxy.updatePalmares(palmares);
        }
        System.out.println("azeraratest3");
        return savedPalmares;
    }
}
