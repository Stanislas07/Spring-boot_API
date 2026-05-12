package com.bibliotheque.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bibliotheque.api.entity.Utilisateur;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    // Vous pouvez aussi définir vos propres requêtes
    // Spring les génère automatiquement à partir du nom de la méthode !
    List<Utilisateur> findByNomContainingIgnoreCase(String nom);
    List<Utilisateur> findByPrenom(String prenom);
    List<Utilisateur> findByNumAdherent(int numAdherent);
}