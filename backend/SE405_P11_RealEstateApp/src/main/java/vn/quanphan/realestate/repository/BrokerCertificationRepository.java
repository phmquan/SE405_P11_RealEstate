package vn.quanphan.realestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vn.quanphan.realestate.domain.BrokerCertification;

public interface BrokerCertificationRepository extends JpaRepository<BrokerCertification, Long>, JpaSpecificationExecutor<BrokerCertification> {

}
