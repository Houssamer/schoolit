package com.schoolit.schoolit.services.confirmationtoken;

import com.schoolit.schoolit.models.ConfirmationToken;
import com.schoolit.schoolit.models.Utilisateur;
import com.schoolit.schoolit.repos.ConfirmationTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ConfirmationTokenService implements IConfirmationTokenService{
    private final ConfirmationTokenRepo confirmationTokenRepo;

    @Autowired
    public ConfirmationTokenService(ConfirmationTokenRepo confirmationTokenRepo) {
        this.confirmationTokenRepo = confirmationTokenRepo;
    }
    @Override
    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepo.save(token);
    }

    @Override
    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepo.findByToken(token);
    }

    @Override
    public void deleteToken(Utilisateur utilisateur) {
        Collection<ConfirmationToken> confirmationTokens = confirmationTokenRepo
                .findConfirmationTokenByUtilisateur(utilisateur);
        confirmationTokenRepo.deleteAll(confirmationTokens);
    }
}
