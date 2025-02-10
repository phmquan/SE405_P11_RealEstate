package vn.quanphan.realestate.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import vn.quanphan.realestate.domain.Listing;
import vn.quanphan.realestate.domain.User;
import vn.quanphan.realestate.util.constant.ListingStatus;

public interface ListingRepository extends JpaRepository<Listing, Long>, JpaSpecificationExecutor<Listing> {

    long countByUser(User user);

    Page<Listing> findByStatus(ListingStatus status, Pageable pageable);

    String countByStatus(ListingStatus status);
}
