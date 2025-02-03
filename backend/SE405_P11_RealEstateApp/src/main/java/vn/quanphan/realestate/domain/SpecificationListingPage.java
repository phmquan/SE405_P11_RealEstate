package vn.quanphan.realestate.domain;

import java.time.Instant;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import vn.quanphan.realestate.util.SecurityUtil;

@Getter
@Setter
@Entity
@Table(name = "specification_listing_page")
public class SpecificationListingPage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String pageName;
    private String fullName;
    private String brokerArea;
    private String workingPlaceAdress;
    @Column(columnDefinition = "MEDIUMTEXT")
    private String description;
    private List<String> listingSpecificationType;

    @OneToOne
    @JoinColumn(name = "broker_certification_id")
    private BrokerCertification brokerCertification;

    @OneToOne(mappedBy = "specificationListingPage")
    private User user;

    private Instant createdAt;
    private Instant updatedAt;
    private String createdBy;
    private String updatedBy;

    @PrePersist
    public void handleBeforeCreate() {
        this.createdBy = SecurityUtil.getCurrentUserLogin().isPresent() == true
                ? SecurityUtil.getCurrentUserLogin().get()
                : "";
        this.createdAt = Instant.now();
    }

    @PreUpdate
    public void handleBeforeUpdate() {
        this.updatedBy = SecurityUtil.getCurrentUserLogin().isPresent() == true
                ? SecurityUtil.getCurrentUserLogin().get()
                : "";
        this.updatedAt = Instant.now();
    }
}
