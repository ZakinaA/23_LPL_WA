package bts.sio.webapp.model;

import lombok.Data;

@Data
public class Olympiade {

    private Integer id;
    private String numero;
    private Integer annee;
    private Ville ville;
}
