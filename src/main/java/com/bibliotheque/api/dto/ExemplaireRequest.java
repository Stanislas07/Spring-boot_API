package com.bibliotheque.api.dto;

import com.bibliotheque.api.entity.Exemplaire;

public class ExemplaireRequest {

    private Exemplaire.Etat etat;
    private boolean disponible;
    private Long livreId;

    public Exemplaire.Etat getEtat() { return etat; }
    public void setEtat(Exemplaire.Etat etat) { this.etat = etat; }

    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }

    public Long getLivreId() { return livreId; }
    public void setLivreId(Long livreId) { this.livreId = livreId; }
}
