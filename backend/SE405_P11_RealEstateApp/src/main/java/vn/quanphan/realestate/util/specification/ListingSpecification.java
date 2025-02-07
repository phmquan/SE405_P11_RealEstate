package vn.quanphan.realestate.util.specification;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import vn.quanphan.realestate.domain.Listing;
import vn.quanphan.realestate.domain.Property;

public class ListingSpecification {

    public static Specification<Listing> hasListingType(String listingType) {
        return (root, query, cb) -> {
            if (listingType == null || listingType.trim().isEmpty()) {
                return cb.conjunction();
            }
            return cb.equal(root.get("listingType"), listingType);
        };
    }

    public static Specification<Listing> hasPropertyType(String propertyType) {
        return (root, query, cb) -> {
            if (propertyType == null || propertyType.trim().isEmpty()) {
                return cb.conjunction();
            }
            return cb.equal(root.get("propertyType"), propertyType);
        };
    }

    public static Specification<Listing> hasPriceBetween(Double minPrice, Double maxPrice) {
        return (root, query, cb) -> {
            // Thực hiện join với entity Property
            Join<Listing, Property> propertyJoin = root.join("property");
            // Truy cập trực tiếp vào trường propertyPrice (kiểu double)
            Path<Double> pricePath = propertyJoin.get("propertyPrice");

            Predicate predicate = cb.conjunction();
            if (minPrice != null) {
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(pricePath, minPrice));
            }
            if (maxPrice != null) {
                predicate = cb.and(predicate, cb.lessThanOrEqualTo(pricePath, maxPrice));
            }
            return predicate;
        };
    }
}
