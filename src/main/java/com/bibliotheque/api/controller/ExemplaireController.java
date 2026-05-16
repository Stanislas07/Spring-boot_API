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
import org.springframework.web.bind.annotation.RestController;

import com.bibliotheque.api.dto.ExemplaireRequest;
import com.bibliotheque.api.entity.Exemplaire;
import com.bibliotheque.api.service.ExemplaireService;

import jakarta.validation.Valid;


@RestController               // Indique que cette classe est un controller REST
@RequestMapping("/api/exemplaires") // Préfixe de toutes les routes de ce controller
public class ExemplaireController {

    @Autowired
    private ExemplaireService exemplaireService;

    // GET /api/exemplaires → Récupérer tous les exemplaires
    @GetMapping
    public ResponseEntity<List<Exemplaire>> getAllExemplaires() {
        List<Exemplaire> exemplaires = exemplaireService.getAllExemplaires();
        return ResponseEntity.ok(exemplaires);    // HTTP 200 + liste des exemplaires en JSON
    }

    // GET /api/exemplaires/{id} → Récupérer un exemplaire par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Exemplaire> getExemplaireById(@PathVariable Long id) {
        return exemplaireService.getExemplaireById(id)
                .map(exemplaire -> ResponseEntity.ok(exemplaire))           // HTTP 200 si trouvé
                .orElse(ResponseEntity.notFound().build());       // HTTP 404 si non trouvé
    }

    // POST /api/exemplaires → Créer un nouveau exemplaire
    @PostMapping
    public ResponseEntity<Exemplaire> createExemplaire(@Valid @RequestBody ExemplaireRequest request) {
        Exemplaire nouveauExemplaire = exemplaireService.createExemplaire(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(nouveauExemplaire);
    }

    
    // PUT /api/exemplaires/{id} → Modifier un exemplaire
    @PutMapping("/{id}")
    public ResponseEntity<Exemplaire> updateExemplaire(@PathVariable Long id,
                                                        @RequestBody Exemplaire exemplaire) {
        return exemplaireService.updateExemplaire(id, exemplaire)
                .map(exemplaireModifie -> ResponseEntity.ok(exemplaireModifie))
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/exemplaires/{id} → Supprimer un exemplaire
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExemplaire(@PathVariable Long id) {
        if (exemplaireService.deleteExemplaire(id)) {
            return ResponseEntity.noContent().build();    // HTTP 204 : succès sans contenu
        }
        return ResponseEntity.notFound().build();         // HTTP 404 si non trouvé
    }

    // GET /api/exemplaires/etat/{etat} → Rechercher par état
    @GetMapping("/etat/{etat}")
    public ResponseEntity<List<Exemplaire>> searchByEtat(@PathVariable Exemplaire.Etat etat) {
        List<Exemplaire> exemplaires = exemplaireService.getByEtat(etat);
        return ResponseEntity.ok(exemplaires);
    }

    // GET /api/exemplaires/disponibles → Récupérer les exemplaires disponibles
    @GetMapping("/disponibles")
    public ResponseEntity<List<Exemplaire>> getDisponibles() {
        List<Exemplaire> exemplairesDisponibles = exemplaireService.getDisponibleTrue();
        return ResponseEntity.ok(exemplairesDisponibles);
    }
}