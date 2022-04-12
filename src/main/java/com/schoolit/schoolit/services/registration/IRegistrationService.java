package com.schoolit.schoolit.services.registration;

import com.schoolit.schoolit.models.Apprenant;
import com.schoolit.schoolit.models.Formateur;
import com.schoolit.schoolit.models.requests.RegistrationRequest;

public interface IRegistrationService {

     Formateur registerFormateur(RegistrationRequest request);
     Apprenant registerApprenant(RegistrationRequest request);
}
