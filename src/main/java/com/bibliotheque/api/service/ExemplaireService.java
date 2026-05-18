package com.bibliotheque.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bibliotheque.api.dto.ExemplaireRequest;
import com.bibliotheque.api.entity.Exemplaire;
import com.bibliotheque.api.entity.Livre;
import com.bibliotheque.api.repository.ExemplaireRepository;
import com.bibliotheque.api.repository.LivreRepository;

@Service    // Indique que cette classe est un service Spring
public class ExemplaireService {

    @Autowired    // Spring injecte automatiquement le repository
    private ExemplaireRepository exemplaireRepository;

    @Autowired 
    private LivreRepository livreRepository;

    // Récupérer tous les exemplaires
    public List<Exemplaire> getAllExemplaires() {
        return exemplaireRepository.findAll();
    }

    // Récupérer un exemplaire par son ID
    public Optional<Exemplaire> getExemplaireById(Long id) {
        return exemplaireRepository.findById(id);
    }

    // Créer un nouveau exemplaire
    public Exemplaire createExemplaire(ExemplaireRequest request) {
        Livre livre = livreRepository.findById(request.getLivreId())
                    .orElseThrow(() -> new IllegalArgumentException("Livre introuvable avec l'id : " + request.getLivreId()));
        
        Exemplaire exemplaire = new Exemplaire();
        exemplaire.setEtat(request.getEtat());
        exemplaire.setLivre(livre);
        
        return exemplaireRepository.save(exemplaire);
    }

    // Modifier un exemplaire existant
    public Optional<Exemplaire> updateExemplaire(Long id, Exemplaire exemplaireModifie) {
        return exemplaireRepository.findById(id).map(exemplaireExistant -> {
            exemplaireExistant.setEtat(exemplaireModifie.getEtat());
            return exemplaireRepository.save(exemplaireExistant);
        });
    }

    // Supprimer un exemplaire
    public boolean deleteExemplaire(Long id) {
        if (exemplaireRepository.existsById(id)) {
            exemplaireRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Filtrer par etat
    public List<Exemplaire> getByEtat(Exemplaire.EtatEnum etat) {
        return exemplaireRepository.findByEtat(etat);
    }
}