package vn.hoidanit.realestate.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.hoidanit.realestate.util.constant.Gender;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResUserDTO {
    private long id;
    private String email;
    private String name;
    private Gender gender;
    private String address;
    private int age;
    private Instant updatedAt;
    private Instant createdAt;
}
