package com.bibliotheque.api.dto;

import com.bibliotheque.api.entity.Utilisateur;

public class UtilisateurRequest {

    private String nom;
    private String prenom;
    private int numeroAdherent;
    private Utilisateur.TypeUtilisateur type;

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public int getNumeroAdherent() { return this.numeroAdherent; }
    public void setNumeroAdherent(int numeroAdherent) { this.numeroAdherent = numeroAdherent; }

    public Utilisateur.TypeUtilisateur getType() { return type ; }
    public void setType(Utilisateur.TypeUtilisateur typeUtilisateur) { this.type = typeUtilisateur; }
}
