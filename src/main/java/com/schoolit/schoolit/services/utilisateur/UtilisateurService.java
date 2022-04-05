package com.schoolit.schoolit.services.utilisateur;

import com.schoolit.schoolit.models.Apprenant;
import com.schoolit.schoolit.models.Formateur;
import com.schoolit.schoolit.repos.UtilisateurRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
