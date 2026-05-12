package com.bibliotheque.api.dto;

public class UtilisateurRequest {

    private String nom;
    private String prenom;
    private int numero_adherent;

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public int getNumero_adherent() { return numero_adherent; }
    public void setNumero_adherent(int numero_adherent) { this.numero_adherent = numero_adherent; }
}
