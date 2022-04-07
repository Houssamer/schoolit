package com.schoolit.schoolit.services.utilisateur;

import com.schoolit.schoolit.Exception.UtilisateurException;
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
    private final UtilisateurRepo<Utilisateur> utilisateurRepo;

    @Autowired
    public UtilisateurService(UtilisateurRepo<Apprenant> apprenantUtilisateurRepo,
                              UtilisateurRepo<Formateur> formateurUtilisateurRepo,
                              UtilisateurRepo<Utilisateur> utilisateurRepo) {
        this.apprenantUtilisateurRepo = apprenantUtilisateurRepo;
        this.formateurUtilisateurRepo = formateurUtilisateurRepo;
        this.utilisateurRepo = utilisateurRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        return null;
    }

    @Override
    public Utilisateur getUtilisateur(Long id) {
        return utilisateurRepo.getById(id);
    }

    @Override
    public Utilisateur getUtilisateurByEmail(String email) {
        return utilisateurRepo
                .findByEmail(email)
                .orElseThrow(() -> new UtilisateurException());
    }

    @Override
    public void ajouterApprenant(Apprenant apprenant) {
        boolean exist  = apprenantUtilisateurRepo.existsById(apprenant.getId());
        if (exist) {
            throw new UtilisateurException("apprenant deja existe");
        } else {
            apprenantUtilisateurRepo.save(apprenant);
        }
    }

    @Override
    public void ajouterFormateur(Formateur formateur) {
        boolean exist = formateurUtilisateurRepo.existsById(formateur.getId());
        if (exist) {
            throw new UtilisateurException("Formateur deja existe");
        } else {
            formateurUtilisateurRepo.save(formateur);
        }
    }

    @Override
    public void deleteFormateur(Long id) {
        boolean exist = formateurUtilisateurRepo.existsById(id);
        if (exist) {
            formateurUtilisateurRepo.deleteById(id);
        } else {
            throw new UtilisateurException("Formateur n'existe pas");
        }
    }

    @Override
    public void modifierUtilisateur(Utilisateur utilisateur) {
        boolean exist = utilisateurRepo.existsById(utilisateur.getId());
        if (exist) {
            utilisateurRepo.save(utilisateur);
        } else {
            throw new UtilisateurException("utilisateur n'existe pas");
        }
    }

    @Override
    public String enableCompte(Long id) {
        Formateur formateur = formateurUtilisateurRepo.getById(id);
        formateur.setEnabled(true);
        formateurUtilisateurRepo.save(formateur);
        return "Done";
    }

    @Override
    public Collection<Formateur> getFormateurs() {
        return formateurUtilisateurRepo.findAllFormateur();
    }

    @Override
    public Collection<Apprenant> getApprenants() {
        return apprenantUtilisateurRepo.findAllApprenant();
    }

    @Override
    public Collection<Formateur> getFormateurNonVerifie() {
        return formateurUtilisateurRepo.findDisbaledFormateur();
    }
}
