package bts.sio.webapp.model;

import lombok.Data;

@Data
public class Palmares {

    private Integer id;
    private Integer annee;
    private Athlete athlete;
    private Medaille medaille;
    private Championnat championnat;
    private Ville ville;

}
