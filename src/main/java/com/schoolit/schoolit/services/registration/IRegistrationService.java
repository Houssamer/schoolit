package com.schoolit.schoolit.services.registration;

import com.schoolit.schoolit.models.requests.RegistrationRequest;

public interface IRegistrationService {

    public void registerFormateur(RegistrationRequest request);
    public void registerApprenant(RegistrationRequest request);
}
