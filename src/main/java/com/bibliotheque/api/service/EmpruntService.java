package com.bibliotheque.api.service;

import com.bibliotheque.api.dto.EmpruntRequest;
import com.bibliotheque.api.entity.Emprunt;
import com.bibliotheque.api.entity.Exemplaire;
import com.bibliotheque.api.entity.Utilisateur;
import com.bibliotheque.api.repository.EmpruntRepository;
import com.bibliotheque.api.repository.ExemplaireRepository;
import com.bibliotheque.api.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EmpruntService {

    @Autowired
    private EmpruntRepository empruntRepository;

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    // Récupérer tous les emprunts
    public List<Emprunt> getAllEmprunts() {
        return empruntRepository.findAll();
    }

    // Récupérer un emprunt par son ID
    public Optional<Emprunt> getEmpruntById(Long id) {
        return empruntRepository.findById(id);
    }

    // Récupérer les emprunts d'un utilisateur
    public List<Emprunt> getEmpruntsByUtilisateur(Long utilisateurId) {
        return empruntRepository.findByUtilisateurId(utilisateurId);
    }

    // Récupérer tous les emprunts en cours (pas encore retournés)
    public List<Emprunt> getEmpruntsEnCours() {
        return empruntRepository.findByDateRetourEffectiveIsNull();
    }

    // Créer un nouvel emprunt
    public Emprunt createEmprunt(EmpruntRequest request) {
        Exemplaire exemplaire = exemplaireRepository.findById(request.getExemplaireId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Exemplaire introuvable avec l'id : " + request.getExemplaireId()));

        // Vérifier que l'exemplaire est disponible
        if (exemplaire.getEtat() != Exemplaire.EtatEnum.DISPONIBLE) {
            throw new IllegalStateException(
                    "L'exemplaire " + request.getExemplaireId() + " n'est pas disponible (état actuel : " + exemplaire.getEtat() + ")");
        }

        Utilisateur utilisateur = utilisateurRepository.findById(request.getUtilisateurId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Utilisateur introuvable avec l'id : " + request.getUtilisateurId()));

        // Passer l'exemplaire en état EMPRUNTE
        exemplaire.setEtat(Exemplaire.EtatEnum.EMPRUNTE);
        exemplaireRepository.save(exemplaire);

        Emprunt emprunt = new Emprunt();
        emprunt.setExemplaire(exemplaire);
        emprunt.setUtilisateur(utilisateur);
        emprunt.setDateEmprunt(LocalDate.now());
        emprunt.setDateRetourPrevue(request.getDateRetourPrevue());

        return empruntRepository.save(emprunt);
    }

    // Enregistrer le retour d'un exemplaire
    public Optional<Emprunt> retournerEmprunt(Long id) {
        return empruntRepository.findById(id).map(emprunt -> {

            if (emprunt.getDateRetourEffective() != null) {
                throw new IllegalStateException("Cet emprunt a déjà été retourné le " + emprunt.getDateRetourEffective());
            }

            // Marquer la date de retour effective
            emprunt.setDateRetourEffective(LocalDate.now());

            // Remettre l'exemplaire en état DISPONIBLE
            Exemplaire exemplaire = emprunt.getExemplaire();
            exemplaire.setEtat(Exemplaire.EtatEnum.DISPONIBLE);
            exemplaireRepository.save(exemplaire);

            return empruntRepository.save(emprunt);
        });
    }

    // Supprimer un emprunt
    public boolean deleteEmprunt(Long id) {
        if (empruntRepository.existsById(id)) {
            empruntRepository.deleteById(id);
            return true;
        }
        return false;
    }
}