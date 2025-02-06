package vn.quanphan.realestate.domain;

import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;

@Entity
@lombok.Getter
@lombok.Setter
@RequiredArgsConstructor
@DiscriminatorValue("apartment")
public class Apartment extends Property {

    private String apartmentCode; // Mã căn hộ
    private String apartmentFloor; // Tầng
    private String apartmentBlock;

    // Căn hộ chung cư, căn hộ officetel, căn hộ duplex, căn hộ penthouse
    @NotBlank(message = "Loại căn hộ không được để trống")
    private String apartmentType;

    private double apartmentRoom; // Số phòng ngủ
    private double apartmentToilet; // Số phòng vệ sinh
    private String apartmentDirection; // Hướng căn hộ

}
