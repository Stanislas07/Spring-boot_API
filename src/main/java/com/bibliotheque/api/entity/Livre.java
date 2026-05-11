package com.bibliotheque.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity                    // Indique que cette classe est une table en BDD
@Table(name = "livres")    // Nom de la table
@Data                      // Lombok : génère getters, setters, toString, equals, hashCode
@NoArgsConstructor         // Lombok : génère le constructeur sans paramètres
@AllArgsConstructor        // Lombok : génère le constructeur avec tous les paramètres
public class Livre {

    @Id                                                    // Clé primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY)    // Auto-incrément
    private Long id;

    @Column(nullable = false)    // La colonne ne peut pas être NULL
    private String titre;

    @Column(nullable = false)
    private String isbn;

    @Enumerated(EnumType.STRING)    // Stocke la catégorie comme texte (ex: "ROMAN")
    private Categorie categorie;

    // Enumération des catégories (définie dans la même classe pour simplifier)
    public enum Categorie {
        ROMAN, NOUVELLE, POESIE, MANGA, ROMANCE, COMEDIE
    }

    // Relations
    
    @ManyToOne
    @JoinColumn(name = "auteur_id")
    private Auteur auteur;
}