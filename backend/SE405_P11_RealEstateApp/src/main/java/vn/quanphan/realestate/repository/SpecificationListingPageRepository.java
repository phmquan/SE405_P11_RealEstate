package vn.quanphan.realestate.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import vn.quanphan.realestate.domain.SpecificationListingPage;
import vn.quanphan.realestate.util.constant.SpecificationListingPageStatus;

public interface SpecificationListingPageRepository extends JpaRepository<SpecificationListingPage, Long>, JpaSpecificationExecutor<SpecificationListingPage> {

    String countByStatus(SpecificationListingPageStatus pending);

    Page<SpecificationListingPage> findByStatus(SpecificationListingPageStatus specificationListingPageStatus, Pageable pageable);

}
