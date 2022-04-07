package com.schoolit.schoolit.services.utilisateur;

import com.schoolit.schoolit.models.Apprenant;
import com.schoolit.schoolit.models.Formateur;
import com.schoolit.schoolit.models.Utilisateur;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

public interface IUtilisateurService {
    Utilisateur getUtilisateur(Long id);
    Utilisateur getUtilisateurByEmail(String email);
    void ajouterApprenant(Apprenant apprenant);
    void ajouterFormateur(Formateur formateur);
    void deleteFormateur(Long id);
    void modifierUtilisateur(Utilisateur utilisateur);
    String enableCompte(Long id);
    Collection<Formateur> getFormateurs();
    Collection<Apprenant> getApprenants();
    Collection<Formateur> getFormateurNonVerifie();
}
