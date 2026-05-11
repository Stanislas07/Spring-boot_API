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

import com.bibliotheque.api.dto.LivreRequest;
import com.bibliotheque.api.entity.Livre;
import com.bibliotheque.api.service.LivreService;


@RestController               // Indique que cette classe est un controller REST
@RequestMapping("/api/livres") // Préfixe de toutes les routes de ce controller
public class LivreController {

    @Autowired
    private LivreService livreService;

    // GET /api/livres → Récupérer tous les livres
    @GetMapping
    public ResponseEntity<List<Livre>> getAllLivres() {
        List<Livre> livres = livreService.getAllLivres();
        return ResponseEntity.ok(livres);    // HTTP 200 + liste des livres en JSON
    }

    // GET /api/livres/{id} → Récupérer un livre par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Livre> getLivreById(@PathVariable Long id) {
        return livreService.getLivreById(id)
                .map(livre -> ResponseEntity.ok(livre))           // HTTP 200 si trouvé
                .orElse(ResponseEntity.notFound().build());       // HTTP 404 si non trouvé
    }

    // POST /api/livres → Créer un nouveau livre
    @PostMapping
    public ResponseEntity<Livre> createLivre(@RequestBody LivreRequest request) {
        Livre nouveauLivre = livreService.createLivre(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(nouveauLivre);  // HTTP 201
    }

    // PUT /api/livres/{id} → Modifier un livre
    @PutMapping("/{id}")
    public ResponseEntity<Livre> updateLivre(@PathVariable Long id,
                                              @RequestBody Livre livre) {
        return livreService.updateLivre(id, livre)
                .map(livreModifie -> ResponseEntity.ok(livreModifie))
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/livres/{id} → Supprimer un livre
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLivre(@PathVariable Long id) {
        if (livreService.deleteLivre(id)) {
            return ResponseEntity.noContent().build();    // HTTP 204 : succès sans contenu
        }
        return ResponseEntity.notFound().build();         // HTTP 404 si non trouvé
    }

    // GET /api/livres/search?titre=harry → Rechercher par titre
    @GetMapping("/search")
    public ResponseEntity<List<Livre>> searchByTitre(@RequestParam String titre) {
        List<Livre> livres = livreService.searchByTitre(titre);
        return ResponseEntity.ok(livres);
    }

    // GET /api/livres/categorie/ROMAN → Filtrer par catégorie
    @GetMapping("/categorie/{categorie}")
    public ResponseEntity<List<Livre>> getByCategorie(
            @PathVariable Livre.Categorie categorie) {
        List<Livre> livres = livreService.getByCategorie(categorie);
        return ResponseEntity.ok(livres);
    }
}