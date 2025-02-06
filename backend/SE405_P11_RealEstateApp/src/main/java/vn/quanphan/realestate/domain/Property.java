package vn.quanphan.realestate.domain;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "properties")
@Getter
@Setter
@RequiredArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
public abstract class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Địa chỉ không được để trống")
    @Size(min = 5, max = 100, message = "Địa chỉ phải có ít nhất 5 kí tự")
    private String address;

    @NotBlank(message = "Quận không được để trống")
    private String district;

    private double propertyLength;

    private double propertyWidth;

    @NotNull(message = "Diện tích không được để trống")
    private Double propertyArea;

    @NotBlank(message = "Thông tin pháp lý bất động sản không được để trống")
    private String legalDocument; // Đã có sổ đỏ, chưa có sổ đỏ

    @NotBlank(message = "Giá không được để trống")
    private String propertyPrice;

    @ElementCollection
    @CollectionTable(name = "property_images", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "property_image_url")
    private List<String> propertyImages;

}
