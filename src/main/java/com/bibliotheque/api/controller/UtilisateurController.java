package com.bibliotheque.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bibliotheque.api.dto.UtilisateurRequest;
import com.bibliotheque.api.entity.Utilisateur;
import com.bibliotheque.api.service.UtilisateurService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    // GET /api/utilisateurs → Récupérer tous les utilisateurs
    @GetMapping
    public ResponseEntity<List<Utilisateur>> getAllUtilisateurs() {
        List<Utilisateur> utilisateurs = utilisateurService.getAllUtilisateurs();
        return ResponseEntity.ok(utilisateurs);
    }

    // GET /api/utilisateurs/{id} → Récupérer un utilisateur par ID
    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable Long id) {
        return utilisateurService.getUtilisateurById(id)
                .map(utilisateur -> ResponseEntity.ok(utilisateur))
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/utilisateurs → Créer un utilisateur
    @PostMapping
    public ResponseEntity<Utilisateur> createUtilisateur(@Valid @RequestBody UtilisateurRequest request) {
            Utilisateur nouveauUtilisateur = utilisateurService.createUtilisateur(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(nouveauUtilisateur);
    }

    // PUT /api/utilisateurs/{id} → Modifier un utilisateur
    @PutMapping("/{id}")
    public ResponseEntity<Utilisateur> updateUtilisateur(
            @PathVariable Long id,
            @Valid @RequestBody Utilisateur utilisateur) {

        return utilisateurService.updateUtilisateur(id, utilisateur)
                .map(utilisateurModifie -> ResponseEntity.ok(utilisateurModifie))
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/utilisateurs/{id} → Supprimer un utilisateur
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable Long id) {
        if (utilisateurService.deleteUtilisateur(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // GET /api/utilisateurs/search?nom=dupont → Rechercher par nom
    @GetMapping("/search")
    public ResponseEntity<List<Utilisateur>> searchByNom(@RequestParam String nom) {
        List<Utilisateur> utilisateurs = utilisateurService.searchByNom(nom);
        return ResponseEntity.ok(utilisateurs);
    }
}
