package bts.sio.webapp.service;
import bts.sio.webapp.model.Medaille;
import bts.sio.webapp.repository.MedailleProxy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class MedailleService {
    @Autowired
    private MedailleProxy medailleProxy;

    public Medaille getMedaille(final int id) {
        return medailleProxy.getMedaille(id);
    }

    public Iterable<Medaille> getMedailles() {
        return medailleProxy.getMedailles();
    }

    public void deleteMedaille(final int id) {
        medailleProxy.deleteMedaille(id);
    }

    public Medaille saveMedaille(Medaille medaille) {
        Medaille savedMedaille;

        medaille.setLibelle(medaille.getLibelle());

        if(medaille.getId() == null) {
            // If id is null, then it is a new Medaille.
            savedMedaille = medailleProxy.createMedaille(medaille);
        } else {
            savedMedaille = medailleProxy.updateMedaille(medaille);
        }

        return savedMedaille;
    }
}
