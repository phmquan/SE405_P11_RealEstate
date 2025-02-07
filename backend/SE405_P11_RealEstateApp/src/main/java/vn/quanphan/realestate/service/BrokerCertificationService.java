package vn.quanphan.realestate.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.quanphan.realestate.domain.BrokerCertification;
import vn.quanphan.realestate.repository.BrokerCertificationRepository;

@Service
@RequiredArgsConstructor
public class BrokerCertificationService {

    private final BrokerCertificationRepository brokerCertificationRepository;

    public BrokerCertification createBrokerCertification(BrokerCertification brokerCertification) {
        BrokerCertification certification = new BrokerCertification();

        certification.setNameOnCertification(brokerCertification.getNameOnCertification());
        certification.setCertificationNumber(brokerCertification.getCertificationNumber());
        certification.setCertificationAuthority(brokerCertification.getCertificationAuthority());
        return brokerCertificationRepository.save(certification);
    }

    public boolean checkExistBrokerCertification(BrokerCertification brokerCertification) {
        return brokerCertificationRepository.existsByNameOnCertificationAndCertificationNumberAndCertificationAuthority(
                brokerCertification.getNameOnCertification(),
                brokerCertification.getCertificationNumber(),
                brokerCertification.getCertificationAuthority()
        );

    }
}
