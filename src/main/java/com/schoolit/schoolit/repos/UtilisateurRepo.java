package com.schoolit.schoolit.repos;

import com.schoolit.schoolit.models.Apprenant;
import com.schoolit.schoolit.models.Formateur;
import com.schoolit.schoolit.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Optional;

public interface UtilisateurRepo<T extends Utilisateur>
                extends JpaRepository<T, Long> {
    Optional<T> findByEmail(String email);
    @Query("select f from Formateur f where f.enabled = false")
    Collection<Formateur> findDisbaledFormateur();
    @Query("select f from Formateur f where f.enabled = true")
    Collection<Formateur> findAllFormateur();
    @Query("select a from Apprenant a")
    Collection<Apprenant> findAllApprenant();
}
