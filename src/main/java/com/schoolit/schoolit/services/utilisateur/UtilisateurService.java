package com.schoolit.schoolit.services.utilisateur;

import com.schoolit.schoolit.models.Apprenant;
import com.schoolit.schoolit.models.Formateur;
import com.schoolit.schoolit.models.Utilisateur;
import com.schoolit.schoolit.repos.UtilisateurRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
public class UtilisateurService implements  IUtilisateurService, UserDetailsService {

    private final UtilisateurRepo<Apprenant> apprenantUtilisateurRepo;
    private final UtilisateurRepo<Formateur> formateurUtilisateurRepo;

    @Autowired
    public UtilisateurService(UtilisateurRepo<Apprenant> apprenantUtilisateurRepo,
                              UtilisateurRepo<Formateur> formateurUtilisateurRepo) {
        this.apprenantUtilisateurRepo = apprenantUtilisateurRepo;
        this.formateurUtilisateurRepo = formateurUtilisateurRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        return null;
    }

    @Override
    public Utilisateur getUtilisateur(Long id) {
        return null;
    }

    @Override
    public Utilisateur getUtilisateurByEmail(String email) {
        return null;
    }

    @Override
    public Apprenant ajouterApprenant(Apprenant apprenant) {
        return null;
    }

    @Override
    public Formateur ajouterFormateur(Formateur formateur) {
        return null;
    }

    @Override
    public void deleteFormateur(Long id) {

    }

    @Override
    public Utilisateur modifierUtilisateur(Utilisateur utilisateur) {
        return null;
    }

    @Override
    public String enableCompte(Long id) {
        return null;
    }

    @Override
    public Collection<Formateur> getFormateurs() {
        return null;
    }

    @Override
    public Collection<Apprenant> getApprenants() {
        return null;
    }

    @Override
    public Collection<Formateur> getFormateurNonVerifie() {
        return null;
    }
}
