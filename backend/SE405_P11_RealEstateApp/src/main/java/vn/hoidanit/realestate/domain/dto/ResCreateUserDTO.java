package vn.hoidanit.realestate.domain.dto;

import lombok.Getter;
import lombok.Setter;
import vn.hoidanit.realestate.util.constant.Gender;

import java.time.Instant;

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