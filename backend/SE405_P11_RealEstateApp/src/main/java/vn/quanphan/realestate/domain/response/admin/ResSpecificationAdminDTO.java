package vn.quanphan.realestate.domain.response.admin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResSpecificationAdminDTO {

    private String id;
    private String name;
    private String description;
    private String nameOneCertification;
    private String brokerCertificationNumber;
    private String status;
    private String totalRow;
}
