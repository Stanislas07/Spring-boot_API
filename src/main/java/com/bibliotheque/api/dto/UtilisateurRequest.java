package com.bibliotheque.api.dto;

public class UtilisateurRequest {

    private String nom;
    private String prenom;
    private int numeroAdherent;

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public int getNumeroAdherent() { return this.numeroAdherent; }
    public void setNumeroAdherent(int numeroAdherent) { this.numeroAdherent = numeroAdherent; }
}
