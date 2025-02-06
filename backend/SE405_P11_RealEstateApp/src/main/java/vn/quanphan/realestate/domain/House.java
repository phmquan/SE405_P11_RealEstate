package vn.quanphan.realestate.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@Entity
@lombok.Getter
@lombok.Setter
@RequiredArgsConstructor
@DiscriminatorValue("house")
public class House extends Property {

    @NotBlank(message = "Loại nhà không được để trống")
    private String houseType; // Nhà mặt phố, nhà trong ngõ, nhà biệt thự, nhà phố liền kề

    @NotNull(message = "Số tầng không được để trống")
    private double houseRoom; // Số phòng ngủ

    private double houseToilet; // Số phòng vệ sinh
    //
    private String houseDirection; // Hướng nhà

}
