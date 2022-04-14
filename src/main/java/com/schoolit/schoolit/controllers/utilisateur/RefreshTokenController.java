package com.schoolit.schoolit.controllers.utilisateur;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.schoolit.schoolit.models.*;
import com.schoolit.schoolit.services.utilisateur.UtilisateurService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/token")
public class RefreshTokenController {
    private final UtilisateurService utilisateurService;

    public RefreshTokenController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @PostMapping("/refresh")
    public ResponseEntity<Map<String,String>> refreshToken(@RequestBody String token)  {
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        String email = decodedJWT.getClaim("email").asString();
        Utilisateur user = utilisateurService.getUtilisateurByEmail(email);
        String accessToken = null;
        String refreshToken;
        if (user instanceof Apprenant) {
            accessToken = JWT.create()
                    .withSubject(user.getUsername())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                    .withClaim("email", user.getEmail())
                    .withClaim("nom", user.getNom())
                    .withClaim("prenom", user.getPrenom())
                    .withClaim("dateNaissance", user.getDateNaissance().toString())
                    .withClaim("role",
                            user.getAuthorities()
                                    .stream()
                                    .map(GrantedAuthority::getAuthority)
                                    .collect(Collectors.toList()))
                    .withClaim("formations", ((Apprenant) user).getFormationsSuivies()
                            .stream().map(Formation::getNom).collect(Collectors.toList()))
                    .withIssuer("schoolit")
                    .sign(algorithm);
        } else if (user instanceof Formateur) {
            accessToken = JWT.create()
                    .withSubject(user.getUsername())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                    .withClaim("email", user.getEmail())
                    .withClaim("nom", user.getNom())
                    .withClaim("prenom", user.getPrenom())
                    .withClaim("dateNaissance", user.getDateNaissance().toString())
                    .withClaim("role",
                            user.getAuthorities()
                                    .stream()
                                    .map(GrantedAuthority::getAuthority)
                                    .collect(Collectors.toList()))
                    .withClaim("formations", ((Formateur) user).getFormationsCrees()
                            .stream().map(Formation::getNom).collect(Collectors.toList()))
                    .withIssuer("schoolit")
                    .sign(algorithm);
        } else if (user instanceof Admin) {
            accessToken = JWT.create()
                    .withSubject(user.getUsername())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                    .withClaim("email", user.getEmail())
                    .withClaim("nom", user.getNom())
                    .withClaim("prenom", user.getPrenom())
                    .withClaim("dateNaissance", user.getDateNaissance().toString())
                    .withClaim("role",
                            user.getAuthorities()
                                    .stream()
                                    .map(GrantedAuthority::getAuthority)
                                    .collect(Collectors.toList()))
                    .withIssuer("schoolit")
                    .sign(algorithm);
        }
        refreshToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 14 * 24 * 60 * 60 * 1000))
                .withClaim("email", user.getEmail())
                .withIssuer("schoolit")
                .sign(algorithm);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", accessToken);
        tokens.put("refresh_token", refreshToken);

        return ResponseEntity.ok().body(tokens);
    }
}
