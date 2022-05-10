package com.schoolit.schoolit.services.utilisateur;

import com.schoolit.schoolit.Exception.UtilisateurException;
import com.schoolit.schoolit.models.Apprenant;
import com.schoolit.schoolit.models.Formateur;
import com.schoolit.schoolit.models.Formation;
import com.schoolit.schoolit.models.Utilisateur;
import com.schoolit.schoolit.repos.UtilisateurRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
public class UtilisateurService implements  IUtilisateurService, UserDetailsService {

    private final UtilisateurRepo<Apprenant> apprenantUtilisateurRepo;
    private final UtilisateurRepo<Formateur> formateurUtilisateurRepo;
    private final UtilisateurRepo<Utilisateur> utilisateurRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UtilisateurService(UtilisateurRepo<Apprenant> apprenantUtilisateurRepo,
                              UtilisateurRepo<Formateur> formateurUtilisateurRepo,
                              UtilisateurRepo<Utilisateur> utilisateurRepo,
                              BCryptPasswordEncoder passwordEncoder) {
        this.apprenantUtilisateurRepo = apprenantUtilisateurRepo;
        this.formateurUtilisateurRepo = formateurUtilisateurRepo;
        this.utilisateurRepo = utilisateurRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        return utilisateurRepo
                .findByEmail(username)
                .orElseThrow(UtilisateurException::new);
    }

    @Override
    public Utilisateur getUtilisateur(Long id) {
        return utilisateurRepo.getById(id);
    }

    @Override
    public Utilisateur getUtilisateurByEmail(String email) {
        return utilisateurRepo
                .findByEmail(email)
                .orElseThrow(UtilisateurException::new);
    }

    @Override
    public void ajouterApprenant(Apprenant apprenant) throws UtilisateurException {
        boolean exist = apprenantUtilisateurRepo.findByEmail(apprenant.getEmail()).isPresent();
        if (!exist) {
            apprenant.setPassword(passwordEncoder.encode(apprenant.getPassword()));
            apprenantUtilisateurRepo.save(apprenant);
        } else {
            throw new UtilisateurException("Apprenant deja existant");
        }
    }

    @Override
    public void ajouterFormateur(Formateur formateur) throws UtilisateurException {
        boolean exist = formateurUtilisateurRepo.findByEmail(formateur.getEmail()).isPresent();
        if (!exist) {
            formateur.setPassword(passwordEncoder.encode(formateur.getPassword()));
            formateurUtilisateurRepo.save(formateur);
        } else {
            throw new UtilisateurException("Formateur deja existant");
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

    @Override
    public Collection<Formation> getFormationsSuivies(Long id) {
        Utilisateur apprenant = utilisateurRepo.getById(id);
        return apprenant.getFormationsSuivies();
    }

    @Override
    public Collection<Formation> getFormationsCrees(Long id) {
        Utilisateur formateur = utilisateurRepo.getById(id);
        return formateur.getFormationsCrees();
    }
}
