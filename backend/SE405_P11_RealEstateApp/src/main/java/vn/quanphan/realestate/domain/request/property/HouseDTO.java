package vn.quanphan.realestate.domain.request.property;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class HouseDTO extends PropertyDTO {

    private String houseType;
    private String houseRoom;
    private String houseToilet;
    private String houseDirection;
}
