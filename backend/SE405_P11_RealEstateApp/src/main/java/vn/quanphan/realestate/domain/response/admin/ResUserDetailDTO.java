package vn.quanphan.realestate.domain.response.admin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResUserDetailDTO {

    private String name;
    private String email;
    private String age;
    private String address;
    private String phone;
    private String status;
    private String avatar;
}
