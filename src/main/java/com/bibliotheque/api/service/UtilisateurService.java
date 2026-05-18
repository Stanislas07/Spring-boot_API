// service/UtilisateurService.java
package com.bibliotheque.api.service;

import com.bibliotheque.api.entity.Utilisateur;
import com.bibliotheque.api.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public List<Utilisateur> getAllUtilisateurs() { return utilisateurRepository.findAll(); }
    public Optional<Utilisateur> getUtilisateurById(Long id) { return utilisateurRepository.findById(id); }
    public Utilisateur createUtilisateur(Utilisateur u) { return utilisateurRepository.save(u); }

    public Optional<Utilisateur> updateUtilisateur(Long id, Utilisateur modifie) {
        return utilisateurRepository.findById(id).map(existant -> {
            existant.setNom(modifie.getNom());
            existant.setPrenom(modifie.getPrenom());
            existant.setEmail(modifie.getEmail());
            existant.setType(modifie.getType());
            return utilisateurRepository.save(existant);
        });
    }

    public boolean deleteUtilisateur(Long id) {
        if (utilisateurRepository.existsById(id)) {
            utilisateurRepository.deleteById(id);
            return true;
        }
        return false;
    }
}