package com.schoolit.schoolit.services.utilisateur;

import com.schoolit.schoolit.Exception.UtilisateurException;
import com.schoolit.schoolit.models.Apprenant;
import com.schoolit.schoolit.models.Formateur;
import com.schoolit.schoolit.models.Formation;
import com.schoolit.schoolit.models.Utilisateur;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

public interface IUtilisateurService {
    Utilisateur getUtilisateur(Long id);
    Utilisateur getUtilisateurByEmail(String email);
    String ajouterApprenant(Apprenant apprenant) throws UtilisateurException;
    String ajouterFormateur(Formateur formateur) throws UtilisateurException;
    void deleteFormateur(Long id);
    void modifierUtilisateur(Utilisateur utilisateur);
    void unlockUtilisateur(String email);
    String enableCompte(Long id);
    Collection<Formateur> getFormateurs();
    Collection<Apprenant> getApprenants();
    Collection<Formateur> getFormateurNonVerifie();
    Collection<Formation> getFormationsSuivies(Long id);
    Collection<Formation> getFormationsCrees(Long id);
}
