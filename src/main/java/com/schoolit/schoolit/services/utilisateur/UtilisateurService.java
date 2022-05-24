package com.schoolit.schoolit.services.utilisateur;

import com.schoolit.schoolit.Exception.UtilisateurException;
import com.schoolit.schoolit.models.*;
import com.schoolit.schoolit.repos.ConfirmationTokenRepo;
import com.schoolit.schoolit.repos.UtilisateurRepo;
import com.schoolit.schoolit.services.confirmationtoken.IConfirmationTokenService;
import com.schoolit.schoolit.services.emailsender.IEmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

@Service
@Transactional
public class UtilisateurService implements  IUtilisateurService, UserDetailsService {

    private final UtilisateurRepo<Apprenant> apprenantUtilisateurRepo;
    private final UtilisateurRepo<Formateur> formateurUtilisateurRepo;
    private final UtilisateurRepo<Utilisateur> utilisateurRepo;
    private final ConfirmationTokenRepo confirmationTokenRepo;
    private final IConfirmationTokenService confirmationTokenService;
    private final IEmailSenderService emailSender;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UtilisateurService(UtilisateurRepo<Apprenant> apprenantUtilisateurRepo,
                              UtilisateurRepo<Formateur> formateurUtilisateurRepo,
                              UtilisateurRepo<Utilisateur> utilisateurRepo,
                              ConfirmationTokenRepo confirmationTokenRepo,
                              IEmailSenderService emailSender,
                              IConfirmationTokenService confirmationTokenService,
                              BCryptPasswordEncoder passwordEncoder) {
        this.apprenantUtilisateurRepo = apprenantUtilisateurRepo;
        this.formateurUtilisateurRepo = formateurUtilisateurRepo;
        this.utilisateurRepo = utilisateurRepo;
        this.confirmationTokenRepo = confirmationTokenRepo;
        this.emailSender = emailSender;
        this.confirmationTokenService = confirmationTokenService;
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
    public String ajouterApprenant(Apprenant apprenant) throws UtilisateurException {
        boolean exist = apprenantUtilisateurRepo.findByEmail(apprenant.getEmail()).isPresent();
        if (!exist) {
            apprenant.setPassword(passwordEncoder.encode(apprenant.getPassword()));
            apprenantUtilisateurRepo.save(apprenant);
            return generateToken(apprenant);
        } else {
            Utilisateur utilisateur = utilisateurRepo.findByEmail(apprenant.getEmail()).get();
            if (utilisateur.getLocked()) {
                return generateToken(utilisateur);
            }
            throw new UtilisateurException("Apprenant deja existant");
        }
    }

    @Override
    public String ajouterFormateur(Formateur formateur) throws UtilisateurException {
        boolean exist = formateurUtilisateurRepo.findByEmail(formateur.getEmail()).isPresent();
        if (!exist) {
            formateur.setPassword(passwordEncoder.encode(formateur.getPassword()));
            formateurUtilisateurRepo.save(formateur);
            return generateToken(formateur);
        } else {
            Utilisateur utilisateur = utilisateurRepo.findByEmail(formateur.getEmail()).get();
            if (utilisateur.getLocked()) {
                return generateToken(utilisateur);
            }
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
            utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
            utilisateurRepo.save(utilisateur);
        } else {
            throw new UtilisateurException("utilisateur n'existe pas");
        }
    }

    @Override
    public void unlockUtilisateur(String email) {
        Utilisateur utilisateur = utilisateurRepo.findByEmail(email)
                .orElseThrow(() -> new UtilisateurException("Utilisateur n'est pas trouve"));
        utilisateur.setLocked(false);
        utilisateurRepo.save(utilisateur);
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

    private String generateToken(Utilisateur utilisateur) {
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken =
                new ConfirmationToken(
                        token,
                        LocalDateTime.now(),
                        LocalDateTime.now().plusMinutes(15),
                        utilisateur
                );
        confirmationTokenRepo.save(confirmationToken);
        String link = "http://localhost:8080/api/token/confirm?token="+token;
        emailSender.send(
                utilisateur.getUsername(),
                buildEmail(utilisateur.getPrenom(), link)
        );

        return token;
    }


    private String buildEmail(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }
}
