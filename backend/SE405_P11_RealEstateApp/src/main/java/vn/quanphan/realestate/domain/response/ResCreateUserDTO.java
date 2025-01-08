package vn.quanphan.realestate.domain.response;

import java.time.Instant;

import lombok.Getter;
import lombok.Setter;
import vn.quanphan.realestate.util.constant.Gender;

@Getter
@Setter
public class ResCreateUserDTO {

    private long id;
    private String name;
    private String email;
    private Gender gender;
    private String address;
    private int age;
    private Instant createdAt;
}
