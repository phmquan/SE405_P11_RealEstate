package vn.quanphan.realestate.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BrokerCertification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long certificationId;

    @NotBlank(message = "Tên trên chứng chỉ không được để trống")
    private String nameOnCertification;

    @NotBlank(message = "Số chứng chỉ không được để trống")
    private String certificationNumber;

    @NotBlank(message = "Cơ quan cấp chứng chỉ không được để trống")
    private String certificationAuthority;

    @OneToOne(mappedBy = "brokerCertification")
    private SpecificationListingPage specificationListingPage;

}
