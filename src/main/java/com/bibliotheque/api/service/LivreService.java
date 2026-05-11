package com.bibliotheque.api.service;

import com.bibliotheque.api.entity.Livre;
import com.bibliotheque.api.repository.LivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service    // Indique que cette classe est un service Spring
public class LivreService {

    @Autowired    // Spring injecte automatiquement le repository
    private LivreRepository livreRepository;

    // Récupérer tous les livres
    public List<Livre> getAllLivres() {
        return livreRepository.findAll();
    }

    // Récupérer un livre par son ID
    public Optional<Livre> getLivreById(Long id) {
        return livreRepository.findById(id);
    }

    // Créer un nouveau livre
    public Livre createLivre(Livre livre) {
        // Règle métier : l'ISBN doit être unique
        // (vous pouvez ajouter des validations ici)
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