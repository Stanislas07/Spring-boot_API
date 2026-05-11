package com.bibliotheque.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bibliotheque.api.entity.Auteur;

@Repository
public interface AuteurRepository extends JpaRepository<Auteur, Long> {
    // JpaRepository<Auteur, Long> signifie :
    //   - Auteur   → le type de l'entité gérée
    //   - Long    → le type de la clé primaire (id)

    // Spring génère automatiquement les méthodes suivantes :
    //   findAll()         → récupère tous les auteurs
    //   findById(id)      → récupère un auteur par son id
    //   save(auteur)       → sauvegarde ou met à jour un auteur
    //   deleteById(id)    → supprime un auteur par son id
    //   existsById(id)    → vérifie si un auteur existe

    // Vous pouvez aussi définir vos propres requêtes
    // Spring les génère automatiquement à partir du nom de la méthode !
    List<Auteur> findByNomContainingIgnoreCase(String nom);
}