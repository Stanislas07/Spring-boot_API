package com.bibliotheque.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bibliotheque.api.dto.UtilisateurRequest;
import com.bibliotheque.api.entity.Utilisateur;
import com.bibliotheque.api.repository.UtilisateurRepository;

@Service    // Indique que cette classe est un service Spring
public class UtilisateurService {

    @Autowired    // Spring injecte automatiquement le repository
    private UtilisateurRepository utilisateurRepository;

    // Récupérer tous les utilisateurs
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    // Récupérer un livre par son ID
    public Optional<Utilisateur> getUtilisateurById(Long id) {
        return utilisateurRepository.findById(id);
    }

    // Créer un nouveau livre
    public Utilisateur createUtilisateur(UtilisateurRequest request) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(request.getNom());
        utilisateur.setPrenom(request.getPrenom());
        utilisateur.setNumero_adherent(request.getNumero_adherent());
        return utilisateurRepository.save(utilisateur);
    }

    // Modifier un livre existant
    public Optional<Utilisateur> updateUtilisateur(Long id, Utilisateur utilisateurModifie) {
        return utilisateurRepository.findById(id).map(utilisateurExistant -> {
            utilisateurExistant.setNom(utilisateurModifie.getNom());
            utilisateurExistant.setPrenom(utilisateurModifie.getPrenom());
            utilisateurExistant.setNumero_adherent(utilisateurModifie.getNumero_adherent());
            return utilisateurRepository.save(utilisateurExistant);
        });
    }

    // Supprimer un utilisateur
    public boolean deleteUtilisateur(Long id) {
        if (utilisateurRepository.existsById(id)) {
            utilisateurRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Rechercher des utilisateurs par titre
    public List<Utilisateur> searchByNom(String nom) {
        return utilisateurRepository.findByNomContainingIgnoreCase(nom);
    }

    // Filtrer par prenom
    public List<Utilisateur> getByCategorie(String prenom) {
        return utilisateurRepository.findByPrenom(prenom);
    }

    // Filtrer par numéro d'adhérent
    public List<Utilisateur> findByNumAdherent(String numAdherent) {
        return utilisateurRepository.findByPrenom(numAdherent);
    }
}