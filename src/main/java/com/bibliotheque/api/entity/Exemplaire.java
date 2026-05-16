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
@Table(name = "exemplaires")    // Nom de la table
@Data                      // Lombok : génère getters, setters, toString, equals, hashCode
@NoArgsConstructor         // Lombok : génère le constructeur sans paramètres
@AllArgsConstructor        // Lombok : génère le constructeur avec tous les paramètres
public class Exemplaire {

    @Id                                                    // Clé primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY)    // Auto-incrément
    private Long id;

    @Enumerated(EnumType.STRING)    // Stocke l'état comme texte (ex: "Neuf")
    private Etat etat;

    // Enumération des états (définie dans la même classe pour simplifier)
    public enum Etat {
        NEUF, BON, MAUVAIS
    }

    @Column(nullable = false)
    private boolean disponible;

    // Relations
    
    @ManyToOne
    @JoinColumn(name = "livre_id")
    private Livre livre;
}