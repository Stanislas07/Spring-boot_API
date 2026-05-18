// repository/EmpruntRepository.java
package com.bibliotheque.api.repository;

import com.bibliotheque.api.entity.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {
    List<Emprunt> findByUtilisateurId(Long utilisateurId);
    List<Emprunt> findByDateRetourEffectiveIsNull();
}