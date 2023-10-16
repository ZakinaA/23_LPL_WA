package bts.sio.webapp.model;

import lombok.Data;

@Data

public class Ville {

    private Integer id;
    private String nom;
    private Pays pays;
}
