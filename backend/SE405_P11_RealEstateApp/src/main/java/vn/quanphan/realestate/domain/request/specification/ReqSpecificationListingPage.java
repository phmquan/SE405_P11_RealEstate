package vn.quanphan.realestate.domain.request.specification;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import vn.quanphan.realestate.domain.BrokerCertification;

@Getter
@Setter
public class ReqSpecificationListingPage {

    private String pageName;
    private String fullName;
    private String brokerArea;
    private String workingPlaceAdress;
    private String description;
    private String avatar;
    private String coverImage;
    private List<String> listingSpecificationType;
    private BrokerCertification brokerCertification;

}
