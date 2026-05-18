package com.bibliotheque.api.controller;

import com.bibliotheque.api.dto.EmpruntRequest;
import com.bibliotheque.api.entity.Emprunt;
import com.bibliotheque.api.service.EmpruntService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emprunts")
public class EmpruntController {

    @Autowired
    private EmpruntService empruntService;

    // GET /api/emprunts → Tous les emprunts
    @GetMapping
    public ResponseEntity<List<Emprunt>> getAllEmprunts() {
        return ResponseEntity.ok(empruntService.getAllEmprunts());
    }

    // GET /api/emprunts/{id} → Un emprunt par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Emprunt> getEmpruntById(@PathVariable Long id) {
        return empruntService.getEmpruntById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/emprunts/utilisateur/{utilisateurId} → Emprunts d'un utilisateur
    @GetMapping("/utilisateur/{utilisateurId}")
    public ResponseEntity<List<Emprunt>> getEmpruntsByUtilisateur(@PathVariable Long utilisateurId) {
        return ResponseEntity.ok(empruntService.getEmpruntsByUtilisateur(utilisateurId));
    }

    // GET /api/emprunts/en-cours → Emprunts non encore retournés
    @GetMapping("/en-cours")
    public ResponseEntity<List<Emprunt>> getEmpruntsEnCours() {
        return ResponseEntity.ok(empruntService.getEmpruntsEnCours());
    }

    // POST /api/emprunts → Créer un emprunt
    @PostMapping
    public ResponseEntity<?> createEmprunt(@Valid @RequestBody EmpruntRequest request) {
        try {
            Emprunt nouvelEmprunt = empruntService.createEmprunt(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(nouvelEmprunt);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    // PATCH /api/emprunts/{id}/retour → Enregistrer le retour d'un exemplaire
    @PatchMapping("/{id}/retour")
    public ResponseEntity<?> retournerEmprunt(@PathVariable Long id) {
        try {
            return empruntService.retournerEmprunt(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    // DELETE /api/emprunts/{id} → Supprimer un emprunt
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmprunt(@PathVariable Long id) {
        if (empruntService.deleteEmprunt(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}