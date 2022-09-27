package com.dev.clinic.service;

import com.dev.clinic.model.Certificate;

public interface CertificateService {
    
    Certificate createCertificate(long registerId, Certificate certificate);

    Certificate updateCertificate(long certificateId, Certificate certificate);

    Boolean deleteCertifcate(long certificateId);
}
