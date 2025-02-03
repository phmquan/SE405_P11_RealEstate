package vn.quanphan.realestate.domain;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;

@lombok.Getter
@lombok.Setter
@Entity
public class Land extends Property {

    @NotBlank(message = "Loại đất không được để trống")
    private String landType; // Đất nền, đất thổ cư, đất nông nghiệp, đất công nghiệp

    private String landDirection; // Hướng đất
}
