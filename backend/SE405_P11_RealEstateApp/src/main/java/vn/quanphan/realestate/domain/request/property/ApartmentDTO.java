package vn.quanphan.realestate.domain.request.property;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ApartmentDTO extends PropertyDTO {

    private String apartmentCode;
    private String apartmentFloor;
    private String apartmentBlock;
    private String apartmentType;
    private String apartmentRoom;
    private String apartmentToilet;
    private String apartmentDirection;
}
