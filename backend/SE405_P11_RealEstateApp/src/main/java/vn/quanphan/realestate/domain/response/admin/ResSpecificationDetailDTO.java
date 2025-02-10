package vn.quanphan.realestate.domain.response.admin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResSpecificationDetailDTO {

    private String pageName;
    private String fullName;
    private String brokerArea;
    private String workingPlaceAdress;
    private BrokerCertification brokerCertification;

    @Getter
    @Setter
    public class BrokerCertification {

        private String nameOnCertification;
        private String certificationNumber;
        private String certificationAuthority;

    }

}
