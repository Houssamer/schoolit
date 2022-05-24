package com.schoolit.schoolit.services.registration;

import com.schoolit.schoolit.Exception.UtilisateurException;
import com.schoolit.schoolit.models.Apprenant;
import com.schoolit.schoolit.models.Formateur;
import com.schoolit.schoolit.models.requests.RegistrationRequest;

public interface IRegistrationService {

     void registerFormateur(RegistrationRequest request) throws UtilisateurException;
     void registerApprenant(RegistrationRequest request) throws UtilisateurException;
     String confirmationToken(String token);
}
