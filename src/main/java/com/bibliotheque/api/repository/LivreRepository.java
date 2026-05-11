package com.bibliotheque.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bibliotheque.api.entity.Livre;

@Repository
public interface LivreRepository extends JpaRepository<Livre, Long> {
    // JpaRepository<Livre, Long> signifie :
    //   - Livre   → le type de l'entité gérée
    //   - Long    → le type de la clé primaire (id)

    // Spring génère automatiquement les méthodes suivantes :
    //   findAll()         → récupère tous les livres
    //   findById(id)      → récupère un livre par son id
    //   save(livre)       → sauvegarde ou met à jour un livre
    //   deleteById(id)    → supprime un livre par son id
    //   existsById(id)    → vérifie si un livre existe

    // Vous pouvez aussi définir vos propres requêtes
    // Spring les génère automatiquement à partir du nom de la méthode !
    List<Livre> findByTitreContainingIgnoreCase(String titre);
    List<Livre> findByCategorie(Livre.Categorie categorie);
}