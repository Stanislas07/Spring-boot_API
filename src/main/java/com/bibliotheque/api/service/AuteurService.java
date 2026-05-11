package com.bibliotheque.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bibliotheque.api.entity.Auteur;
import com.bibliotheque.api.repository.AuteurRepository;

@Service    // Indique que cette classe est un service Spring
public class AuteurService {

    @Autowired    // Spring injecte automatiquement le repository
    private AuteurRepository auteurRepository;

    // Récupérer tous les auteur
    public List<Auteur> getAllAuteur() {
        return auteurRepository.findAll();
    }

    // Récupérer un auteur par son ID
    public Optional<Auteur> getAuteurById(Long id) {
        return auteurRepository.findById(id);
    }

    // Créer un nouveau auteur
    public Auteur createAuteur(Auteur auteur) {
        // Règle métier : l'ISBN doit être unique
        // (vous pouvez ajouter des validations ici)
        return auteurRepository.save(auteur);
    }

    // Modifier un Auteur existant
    public Optional<Auteur> updateAuteur(Long id, Auteur auteurModifie) {
        return auteurRepository.findById(id).map(auteurExistant -> {
            auteurModifie.setNom(auteurModifie.getNom());
            auteurModifie.setPrenom(auteurModifie.getPrenom());
            return auteurRepository.save(auteurModifie);
        });
    }

    // Supprimer un Auteur
    public boolean deleteAuteur(Long id) {
        if (auteurRepository.existsById(id)) {
            auteurRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Rechercher des Auteur par nom
    public List<Auteur> searchByNom(String nom) {
        return auteurRepository.findByNomContainingIgnoreCase(nom);
    }
}