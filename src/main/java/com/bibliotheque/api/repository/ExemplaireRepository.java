package com.bibliotheque.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bibliotheque.api.entity.Exemplaire;

@Repository
public interface ExemplaireRepository extends JpaRepository<Exemplaire, Long> {
    List<Exemplaire> findByLivreId(Long livreId);
    List<Exemplaire> findByEtat(Exemplaire.EtatEnum etat);
}