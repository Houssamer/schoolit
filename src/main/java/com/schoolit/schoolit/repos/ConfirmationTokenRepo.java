package com.schoolit.schoolit.repos;

import com.schoolit.schoolit.models.ConfirmationToken;
import com.schoolit.schoolit.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface ConfirmationTokenRepo extends JpaRepository<ConfirmationToken, Long> {
    Optional<ConfirmationToken> findByToken(String token);
    Collection<ConfirmationToken> findConfirmationTokenByUtilisateur(Utilisateur utilisateur);
}
