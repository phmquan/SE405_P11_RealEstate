package vn.quanphan.realestate.domain;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.quanphan.realestate.util.SecurityUtil;
import vn.quanphan.realestate.util.constant.ListingStatus;

@Entity
@Table(name = "listings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Listing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)  // Foreign key for agency
    private User user;

    private String listingType; // sale, rent

    @NotBlank(message = "Tiêu đề không được để trống")
    @Size(min = 5, max = 50, message = "Tiêu đề phải có ít nhất 5 kí tự")
    private String listingTitle;

    @NotBlank(message = "Mô tả không được để trống")
    @Size(min = 20, max = 1500, message = "Mô tả phải có ít nhất 20 kí tự")
    @Column(columnDefinition = "MEDIUMTEXT")
    private String listingDescription;
    @NotBlank(message = "Loại bất động sản không được để trống")
    private String propertyType;
    // House, Apartment, Land
    @Enumerated(EnumType.STRING)
    private ListingStatus status;
    private Instant createdAt;
    private Instant updatedAt;
    private String createdBy;
    private String updatedBy;

    @Valid
    @OneToOne
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;

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
