package vn.quanphan.realestate.domain.response;

import lombok.Getter;

@Getter
@lombok.Setter
public class ResSpecificationListingPageDTO {

    private long id;
    private String pageName;
    private String fullName;
    private String brokerArea;
    private String workingPlaceAdress;
    private String description;
    private java.util.List<String> listingSpecificationType;
    private vn.quanphan.realestate.domain.BrokerCertification brokerCertification;
    private vn.quanphan.realestate.domain.response.ResUserDTO user;
    private String status;
    private String avartar;
    private String coverImage;

    private java.time.Instant createdAt;
}
