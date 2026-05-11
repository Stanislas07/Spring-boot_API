package com.bibliotheque.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bibliotheque.api.dto.LivreRequest;
import com.bibliotheque.api.entity.Auteur;
import com.bibliotheque.api.entity.Livre;
import com.bibliotheque.api.repository.AuteurRepository;
import com.bibliotheque.api.repository.LivreRepository;

@Service    // Indique que cette classe est un service Spring
public class LivreService {

    @Autowired    // Spring injecte automatiquement le repository
    private LivreRepository livreRepository;

    @Autowired 
    private AuteurRepository auteurRepository;

    // Récupérer tous les livres
    public List<Livre> getAllLivres() {
        return livreRepository.findAll();
    }

    // Récupérer un livre par son ID
    public Optional<Livre> getLivreById(Long id) {
        return livreRepository.findById(id);
    }

    // Créer un nouveau livre
    public Livre createLivre(LivreRequest request) {
        Livre livre = new Livre();
        livre.setTitre(request.getTitre());
        livre.setIsbn(request.getIsbn());
        livre.setCategorie(request.getCategorie());
        if(request.getAuteurId() != null) {
            Auteur auteur = auteurRepository.findById(request.getAuteurId())
                    .orElseThrow(() -> new IllegalArgumentException("Auteur introuvable avec l'id : " + request.getAuteurId()));
            livre.setAuteur(auteur);
        }
        return livreRepository.save(livre);
    }

    // Modifier un livre existant
    public Optional<Livre> updateLivre(Long id, Livre livreModifie) {
        return livreRepository.findById(id).map(livreExistant -> {
            livreExistant.setTitre(livreModifie.getTitre());
            livreExistant.setIsbn(livreModifie.getIsbn());
            livreExistant.setCategorie(livreModifie.getCategorie());
            return livreRepository.save(livreExistant);
        });
    }

    // Supprimer un livre
    public boolean deleteLivre(Long id) {
        if (livreRepository.existsById(id)) {
            livreRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Rechercher des livres par titre
    public List<Livre> searchByTitre(String titre) {
        return livreRepository.findByTitreContainingIgnoreCase(titre);
    }

    // Filtrer par catégorie
    public List<Livre> getByCategorie(Livre.Categorie categorie) {
        return livreRepository.findByCategorie(categorie);
    }
}