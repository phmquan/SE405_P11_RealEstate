package vn.quanphan.realestate.domain.response.admin;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResUserAdminDTO {

    private UUID id;
    private String email;
    private String name;
    private String phone;
    private String status;
    private String totalRow;
}
