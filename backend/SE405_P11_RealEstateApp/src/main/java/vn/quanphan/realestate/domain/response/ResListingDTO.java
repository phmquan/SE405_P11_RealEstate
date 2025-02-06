package vn.quanphan.realestate.domain.response;

import java.time.Instant;

import lombok.Getter;
import lombok.Setter;
import vn.quanphan.realestate.domain.request.property.PropertyDTO;

@Getter
@Setter
public class ResListingDTO {

    private long id;
    private String listingType;
    private String propertyType;
    private String listingTitle;
    private String listingDescription;
    private String listingStatus;
    private Instant createdAt;
    private PropertyDTO property;
}
