package vn.quanphan.realestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import vn.quanphan.realestate.domain.SpecificationListingPage;

public interface SpecificationListingPageRepository extends JpaRepository<SpecificationListingPage, Long>, JpaSpecificationExecutor<SpecificationListingPage> {

}
