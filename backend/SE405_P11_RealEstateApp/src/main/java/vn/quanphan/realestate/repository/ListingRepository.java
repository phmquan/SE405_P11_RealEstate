package vn.quanphan.realestate.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import vn.quanphan.realestate.domain.Listing;
import vn.quanphan.realestate.domain.User;

public interface ListingRepository extends JpaRepository<Listing, Long>, JpaSpecificationExecutor<Listing> {

    long countByUser(User user);
}
