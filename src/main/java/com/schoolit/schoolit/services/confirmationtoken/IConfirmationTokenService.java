package com.schoolit.schoolit.services.confirmationtoken;

import com.schoolit.schoolit.models.ConfirmationToken;
import com.schoolit.schoolit.models.Utilisateur;

import java.util.Optional;

public interface IConfirmationTokenService {
    void saveConfirmationToken(ConfirmationToken token);
    Optional<ConfirmationToken> getToken(String token);
    void deleteToken(Utilisateur utilisateur);
}
