package vn.quanphan.realestate.domain.request;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import vn.quanphan.realestate.domain.request.property.PropertyDTO;

@Getter
@Setter
public class ReqListingDTO {

    private String listingType;
    private String propertyType;
    private String listingTitle;
    private String listingDescription;
    @Valid
    private PropertyDTO property;

}
