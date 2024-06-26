package bts.sio.webapp.model;


import lombok.Data;

import java.time.LocalDate;

@Data
public class Athlete {

    private Integer id;
    private String nom;
    private String prenom;
    private LocalDate date_naissance;
    private String image;
    private Pays pays;
    private Sport sport;


}
