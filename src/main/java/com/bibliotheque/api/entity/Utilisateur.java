package com.bibliotheque.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity                    // Indique que cette classe est une table en BDD
@Table(name = "utilisateurs")    // Nom de la table
@Data                      // Lombok : génère getters, setters, toString, equals, hashCode
@NoArgsConstructor         // Lombok : génère le constructeur sans paramètres
@AllArgsConstructor        // Lombok : génère le constructeur avec tous les paramètres
public class Utilisateur {

    @Id                                                    // Clé primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY)    // Auto-incrément
    private Long id;

    @Column(nullable = false)    // La colonne ne peut pas être NULL
    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @Column(nullable = false)
    @NotBlank(message = "Le prénom est obligatoire")
    private String prenom;

    @Column(name = "numeroAdherent", nullable = false, unique = true)
    @Min(value = 1, message = "Le numero d'adhérent doit être positif")
    private int numeroAdherent;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeUtilisateur type = TypeUtilisateur.PUBLIC;

    public enum TypeUtilisateur {
        ETUDIANT, PERSONNEL, PUBLIC
    }
}