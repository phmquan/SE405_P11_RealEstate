package vn.quanphan.realestate.domain.request.property;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class LandDTO extends PropertyDTO {

    private String landType;
    private String landDirection;
}
