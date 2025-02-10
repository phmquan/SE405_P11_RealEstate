package vn.quanphan.realestate.domain.response.admin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResListingDetailDTO {

    private String userEmail;
    private String listingType;
    private String propertyType;
    private Property property;

    @Getter
    @Setter
    public class Property {

        private String address;
        private String district;
        private String propertyArea;
        private String legalDocument;

    }
}
