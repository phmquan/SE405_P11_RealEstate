package vn.quanphan.realestate.domain.response.admin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResListingAdminDTO {

    private String id;
    private String propertyType;
    private String businessType;
    private String price;
    private String userListing;
    private String status;
    private String totalRow;
}
