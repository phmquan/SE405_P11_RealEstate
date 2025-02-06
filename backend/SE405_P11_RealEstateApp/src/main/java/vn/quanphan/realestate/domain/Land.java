package vn.quanphan.realestate.domain;

import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;

@lombok.Getter
@lombok.Setter
@Entity
@RequiredArgsConstructor
@DiscriminatorValue("land")
public class Land extends Property {

    @NotBlank(message = "Loại đất không được để trống")
    private String landType; // Đất nền, đất thổ cư, đất nông nghiệp, đất công nghiệp

    private String landDirection; // Hướng đất

}
