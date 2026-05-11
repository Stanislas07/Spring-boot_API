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

import com.bibliotheque.api.entity.Auteur;
import com.bibliotheque.api.service.AuteurService;

@RestController               // Indique que cette classe est un controller REST
@RequestMapping("/api/auteurs") // Préfixe de toutes les routes de ce controller
public class AuteurController {

    @Autowired
    private AuteurService auteurService;

    // GET /api/auteurs → Récupérer tous les auteurs
    @GetMapping
    public ResponseEntity<List<Auteur>> getAllAuteurs() {
        List<Auteur> auteurs = auteurService.getAllAuteur();
        return ResponseEntity.ok(auteurs);    // HTTP 200 + liste des auteurs en JSON
    }

    // GET /api/auteurs/{id} → Récupérer un auteur par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Auteur> getAuteurById(@PathVariable Long id) {
        return auteurService.getAuteurById(id)
                .map(auteur -> ResponseEntity.ok(auteur))           // HTTP 200 si trouvé
                .orElse(ResponseEntity.notFound().build());       // HTTP 404 si non trouvé
    }

    // POST /api/auteurs → Créer un nouveau auteur
    @PostMapping
    public ResponseEntity<Auteur> createAuteur(@RequestBody Auteur auteur) {
        Auteur nouveauAuteur = auteurService.createAuteur(auteur);
        return ResponseEntity.status(HttpStatus.CREATED).body(nouveauAuteur);  // HTTP 201
    }

    // PUT /api/auteurs/{id} → Modifier un auteur
    @PutMapping("/{id}")
    public ResponseEntity<Auteur> updateAuteur(@PathVariable Long id,
                                                @RequestBody Auteur auteur) {
        return auteurService.updateAuteur(id, auteur)
                .map(auteurModifie -> ResponseEntity.ok(auteurModifie))
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/auteurs/{id} → Supprimer un auteur
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuteur(@PathVariable Long id) {
        if (auteurService.deleteAuteur(id)) {
            return ResponseEntity.noContent().build();    // HTTP 204 : succès sans contenu
        }
        return ResponseEntity.notFound().build();         // HTTP 404 si non trouvé
    }

    // GET /api/auteurs/search?nom=tolkien → Rechercher par nom
    @GetMapping("/search")
    public ResponseEntity<List<Auteur>> searchByNom(@RequestParam String nom) {
        List<Auteur> auteurs = auteurService.searchByNom(nom);
        return ResponseEntity.ok(auteurs);
    }
}