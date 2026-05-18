package com.bibliotheque.api.dto;

import com.bibliotheque.api.entity.Exemplaire;

public class ExemplaireRequest {

    private Exemplaire.EtatEnum etat;
    private Long livreId;

    public Exemplaire.EtatEnum getEtat() { return etat; }
    public void setEtat(Exemplaire.EtatEnum etat) { this.etat = etat; }

    public Long getLivreId() { return livreId; }
    public void setLivreId(Long livreId) { this.livreId = livreId; }
}
